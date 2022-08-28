package com.zhuyunhao.www.service;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.dao.UserDao;
import com.zhuyunhao.www.po.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 单例模式
 * 用户业务处理
 * @author 朱云皓
 */
public class UserService {

    private static class serviceHolder{
        private static final UserService INSTANCE = new UserService();
    }
    public static UserService getInstance(){
        return serviceHolder.INSTANCE;
    }

    private UserDao userDao = UserDao.getInstance();

    private User user;
    private String userAge;                   //用户网龄
    private boolean isOkClicked = false;      //填写信息时是否按下确定
    private User selectedUser = null;         //存放当前选中的用户


    /**
     * 处理用户登录
     * @param username 输入的用户名
     * @param password 输入的密码
     * @return true 则密码正确
     */
    public boolean login(String username, String password){
        user = userDao.loadUser(username);
        if(password.equals(user.getPassword())){
            setUserAge(user.getIdentityNum());
            return true;
        }else{
            user = null;
            return false;
        }
    }

    /**
     * 检查用户名是否存在
     * @param username 输入的用户名
     * @return true 则存在
     */
    public boolean checkUserName(String username){
        return  userDao.isUserExist(username);
    }

    /**
     * 处理用户注册
     * @param registerData 注册数据
     * @return true则注册成功
     */
    public boolean register(Map<String,String> registerData){
        boolean result = userDao.register(registerData);
        if(result) {
            //得到用户角色类别并分配身份证前缀
            String roleCode = QueryDao.getInstance().queryCode(Integer.parseInt(registerData.get("roleId")));
            //把前缀和日期串结合
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            roleCode += df.format(date);
            //生成身份证号
            userDao.idGenerate(roleCode,registerData.get("username"),registerData.get("roleId"));
            return true;
        }
        return false;
    }
    /**
     * 加载用户列表
     */
    public ObservableList<User> loadUserList() {
        ObservableList<User> userNames = FXCollections.observableArrayList();
        List<User> userList =  userDao.loadAllUser();
        userNames.addAll(userList);
        return userNames;
    }

    /**
     * 处理用户信息自行修改
     * @param userData 用户要修改的数据
     * @return true则修改成功
     */
    public boolean edit(Map<String,String> userData) {
        //添加用户id数据
        userData.put("userId", String.valueOf(user.getUserId()));
        boolean result = userDao.editUser(userData);
        if(result){
            user = userDao.loadUser(userData.get("username"));
        }
        return result;
    }

    /**
     * 处理管理员编辑用户信息
     * @param userData 修改数据
     * @return true则成功
     */
    public boolean adminEdit(Map<String,String> userData, boolean isNew) {
        //尝试写入用户数据
        if(isNew){
            //若是新建用户，则注册
            return register(userData);
        }else{
            return userDao.editUser(userData);
        }
    }

    /**
     * 处理用户删除
     * @param user 用户
     */
    public void delete(User user){
        String userId = user.getUserId();
        userDao.deleteUser(userId);
    }

    /**
     * 处理记住用户业务
     * @param userInfo 记住的数据
     */
    public void rememberUser(List<Object> userInfo){
        userDao.remember(userInfo);
    }

    /**
     * 加载记住的用户名和密码
     * @return 登录数据集
     */
    public Map<String,Object> loadRemember(){
        return userDao.loadRemember();
    }

    /**
     * 计算用户网龄
     * @param identityNum 身份证号
     */
    public void setUserAge(String identityNum) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            long ageTime = new Date().getTime()-df.parse(identityNum.substring(6,14)).getTime();
            this.userAge = String.valueOf((int) (ageTime/1000/60/60/24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 鉴定用户权限
     * @param perCode 权限标识符
     * @return true则有权限
     */
    public boolean authorize(String perCode){
        return userDao.checkPermission(Integer.parseInt(user.getUserId()),perCode);
    }

    public boolean authorize(int userId, String perCode){
        return userDao.checkPermission(userId,perCode);
    }

    public String getUserAge() {
        return userAge;
    }

    public User getUser() {
        return user;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setOkClicked(boolean okClicked) {
        isOkClicked = okClicked;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}