package com.zhuyunhao.www.po;

import javafx.beans.property.*;

/**
 * 与车对应的实体类
 * @author 朱云皓
 */
public class Car {

    private  StringProperty carId;                     //车id
    private  StringProperty carNum;                    //车牌号
    private  StringProperty carName;                   //车名称
    private  StringProperty carStatus;                 //车状态
    private  StringProperty carAge;                    //车龄
    private  StringProperty carPrice;                  //出租价格
    private  StringProperty carSeat;                   //座位数
    private  StringProperty carVolume;                 //车排量
    private  StringProperty carIntroduction;           //车辆介绍

    public Car() {
        this(null,null,null,null,null,null,null,null,null);
    }

    //初始化动态数据
    public Car(String carId, String carNum, String carName, String carStatus, String carAge, String carPrice, String carSeat, String carVolume, String carIntroduction) {
        this.carId = new SimpleStringProperty(carId);
        this.carName =new SimpleStringProperty(carName) ;
        this.carNum =new SimpleStringProperty(carNum) ;
        this.carAge = new SimpleStringProperty(carStatus);
        this.carPrice = new SimpleStringProperty(carAge);
        this.carSeat = new SimpleStringProperty(carPrice) ;
        this.carVolume = new SimpleStringProperty(carSeat) ;
        this.carIntroduction = new SimpleStringProperty(carVolume);
        this.carStatus = new SimpleStringProperty(carIntroduction);;
    }

    public String getCarId() {
        return carId.get();
    }

    public StringProperty carIdProperty() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId.set(String.valueOf(carId));
    }

    public String getCarNum() {
        return carNum.get();
    }

    public void setCarNum(String carNum) {
        this.carNum.set(carNum);
    }

    public String getCarName() {
        return carName.get();
    }

    public StringProperty carNameProperty() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName.set(carName);
    }

    public String getCarStatus() {
        return carStatus.get();
    }

    public StringProperty carStatusProperty() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus.set(carStatus);
    }

    public String getCarAge() {
        return carAge.get();
    }

    public void setCarAge(int carAge) {
        this.carAge.set(String.valueOf(carAge));
    }

    public String getCarPrice() {
        return carPrice.get();
    }

    public void setCarPrice(double carPrice) {
        this.carPrice.set(String.valueOf(carPrice));
    }

    public String getCarSeat() {
        return carSeat.get();
    }

    public void setCarSeat(int carSeat) {
        this.carSeat.set(String.valueOf(carSeat));
    }

    public String getCarVolume() {
        return carVolume.get();
    }

    public void setCarVolume(double carVolume) {
        this.carVolume.set(String.valueOf(carVolume));
    }

    public String getCarIntroduction() {
        return carIntroduction.get();
    }

    public void setCarIntroduction(String carIntroduction) {
        this.carIntroduction.set(carIntroduction);
    }
}
