package com.zhuyunhao.www.po;

import com.zhuyunhao.www.dao.QueryDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 与用户对应的实体类
 * @author 朱云皓
 */
public class User {

    private StringProperty userId;                 //用户主键id
    private StringProperty userName;               //用户名
    private StringProperty password;               //密码
    private StringProperty telephone;              //手机号
    private StringProperty introduction;           //个性签名
    private StringProperty identityNum;            //身份证号
    private StringProperty roleId;                 //用户类型id
    private StringProperty role;                   //用户类型

    public User() {
        this(null, null, null, null, null, null, null, null);
    }

    //初始化动态数据
    public User(String userId, String userName, String password, String telephone, String introduction, String identityNum, String roleId, String roleName) {
        this.userId = new SimpleStringProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.telephone = new SimpleStringProperty(telephone);
        this.introduction = new SimpleStringProperty(introduction);
        this.identityNum = new SimpleStringProperty(identityNum);
        this.roleId = new SimpleStringProperty(roleId);
        this.role = new SimpleStringProperty(roleName);
    }

    public void setRoleId(String roleId) {
        this.roleId.set(roleId);
    }

    public StringProperty roleProperty() {
        return role;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(int roleId) {
        this.role.set(QueryDao.getInstance().queryRoleName(roleId));
    }

    public String getUserId() {
        return userId.get();
    }

    public StringProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(String.valueOf(userId));
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public String getIdentityNum() {
        return identityNum.get();
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum.set(identityNum);
    }

    public String getRoleId() {
        return roleId.get();
    }

}