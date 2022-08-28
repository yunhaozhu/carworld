package com.zhuyunhao.www.dao;

import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.util.JDBCUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单例模式
 * 读取用户数据类
 * @author 朱云皓
 */
public class UserDao {

    private static class daoHolder{
        private static final UserDao INSTANCE = new UserDao();
    }
    public static UserDao getInstance(){
        return daoHolder.INSTANCE;
    }

    /**
     * 填充单个用户数据
     * @param rs 结果集
     * @return 一个用户
     * @throws SQLException 异常
     */
    public User setUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setIdentityNum(rs.getString("identity_num"));
        user.setRoleId(rs.getString("role_id"));
        user.setRole(rs.getInt("role_id"));
        user.setIntroduction(rs.getString("introduction"));
        user.setTelephone(rs.getString("telephone"));
        return user;
    }

    /**
     * 从数据库中加载用户
     * @param username 用户名
     * @return user对象
     */
    public User loadUser(String username){
        List<Object> param = new ArrayList<>();
        param.add(username);
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        ResultSet rs = JDBCUtils.executeQuery(sql, param);
        try {
            if(rs.next()){
                return setUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<User> loadAllUser(){
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM userinfo";
        ResultSet rs =JDBCUtils.executeQuery(sql, null);
        try {
            while(rs.next()){
                userList.add(setUser(rs));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 检查用户名是否存在
     * @param userName 用户名
     * @return true则存在
     */
    public boolean isUserExist(String userName){
        List<Object> param = new ArrayList<>();
        param.add(userName);
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        ResultSet rs = JDBCUtils.executeQuery(sql, param);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 将新用户加入数据库
     * @param registerData 注册数据
     * @return 成功返回true
     */
    public boolean register(Map<String,String> registerData){
        List<Object> param = new ArrayList<>();
        String sql = "INSERT INTO userinfo values(?,?,?,?,?,?,?)";
        param.add(null);
        param.add(registerData.get("username"));
        param.add(registerData.get("password"));
        param.add(registerData.get("telephone"));
        param.add(registerData.get("introduction"));
        param.add(null);
        param.add(registerData.get("roleId"));
        return JDBCUtils.executeUpdate(sql, param)==1;
    }

    /**
     * 生成身份证号并注入数据表
     * @param code 身份证号前缀
     * @param username 用户名
     * @param roleId 角色id
     */
    public void idGenerate(String code, String username, String roleId){
        List<Object> param = new ArrayList<>();
        int userId = QueryDao.getInstance().queryUserIdByUsername(username);
        String sql = "UPDATE userinfo set identity_num = ? where id = ?";
        param.add(String.format("%s%s%s", code, userId, roleId));   //前缀+用户id+用户类型id
        param.add(userId);
        JDBCUtils.executeUpdate(sql, param);
    }

    /**
     * 修改数据库中的用户信息
     * @param userData 修改数据
     * @return true 则修改成功
     */
    public boolean editUser(Map<String, String> userData){
        List<Object> param = new ArrayList<>();
        String sql = "UPDATE userinfo SET username = ?,password = ?,telephone = ?,introduction = ? WHERE id = ?;";
        param.add(userData.get("username"));
        param.add(userData.get("password"));
        param.add(userData.get("telephone"));
        param.add(userData.get("introduction"));
        param.add(userData.get("userId"));
        return JDBCUtils.executeUpdate(sql, param)==1;
    }

    /**
     * 删除指定用户
     * @param userId 用户id
     */
    public void deleteUser(String userId) {
        List<Object> param = new ArrayList<>();
        String sql = "DELETE FROM userinfo where id = ?;";
        param.add(userId);
        JDBCUtils.executeUpdate(sql, param);
    }


    /**
     * 写入记住的登录信息
     * @param param sql参数
     */
    public void remember(List<Object> param) {
        String sql = "UPDATE data SET is_selected = ?,username = ?,password = ? WHERE id = 1;";
        JDBCUtils.executeUpdate(sql, param);
    }

    /**
     * 加载记住的用户名密码
     * @return  登录数据集
     */
    public Map<String,Object> loadRemember(){
        String sql = "SELECT * FROM data;";
        ResultSet rs = JDBCUtils.executeQuery(sql, null);
        Map<String,Object> remembered = new HashMap<>();
        try {
            if(rs.next()){
                remembered.put("isSelected",rs.getBoolean("is_selected"));   //勾选状态
                remembered.put("username",rs.getString("username"));
                remembered.put("password",rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return remembered;
    }

    /**
     * 查询用户权限
     * @param userId id
     * @param perCode 权限标识符
     * @return true则有权限，false没有
     */
    public boolean checkPermission(int userId,String perCode){
        String sql = "SELECT * FROM role_permission WHERE role_id = (SELECT role_id FROM userinfo WHERE id = ?)AND permission_id = ( SELECT id FROM permission WHERE percode = ?)";
        List<Object> param = new ArrayList<>();
        param.add(userId);
        param.add(perCode);
        ResultSet rs = JDBCUtils.executeQuery(sql,param);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
