package com.zhuyunhao.www.po;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 与记录对应的实体类
 * @author 朱云皓
 */
public class Log {

    private  StringProperty logId;              //记录id
    private  StringProperty carNum;             //车牌号
    private  StringProperty userId;             //用户id
    private  StringProperty userName;           //用户名
    private  StringProperty userPhone;          //用户电话
    private  StringProperty date;               //记录日期
    private  StringProperty type;               //记录类型


    public Log() {
        this(null,null,null,null,null,null,null);
    }

    //初始化动态数据
    public Log(String logId, String logName, String logType, String logAge, String carName, String logHealth, String logSkill) {
        this.logId = new SimpleStringProperty(logId);
        this.userName = new SimpleStringProperty(logName);
        this.date = new SimpleStringProperty(logType);
        this.type = new SimpleStringProperty(logAge);
        this.carNum = new SimpleStringProperty(carName);
        this.userPhone = new SimpleStringProperty(logHealth);
        this.userId = new SimpleStringProperty(logSkill);
    }

    public String getLogId() {
        return logId.get();
    }

    public void setLogId(int logId) {
        this.logId.set(String.valueOf(logId));
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getCarNum() {
        return carNum.get();
    }

    public StringProperty carNumProperty() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum.set(carNum);
    }

    public String getUserPhone() {
        return userPhone.get();
    }

    public void setUserPhone(String userPhone) {
        this.userPhone.set(userPhone);
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }
}