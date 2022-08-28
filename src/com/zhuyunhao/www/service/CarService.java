package com.zhuyunhao.www.service;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.dao.CarDao;
import com.zhuyunhao.www.po.Car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

/**
 * 单例模式
 * 车业务处理
 * @author 朱云皓
 */
public class CarService {

    private static class serviceHolder{
        private static final CarService INSTANCE = new CarService();
    }
    public static CarService getInstance(){
        return CarService.serviceHolder.INSTANCE;
    }

    private CarDao carDao = CarDao.getInstance();

    private LogService logService = LogService.getInstance();

    private Car selectedCar = null;              //存放当前选中的车

    /**
     * 加载车列表
     */
    public ObservableList<Car> loadCarList() {
        ObservableList<Car> carNames = FXCollections.observableArrayList();
        List<Car> carList =  carDao.loadAllCar();
        carNames.addAll(carList);
        return carNames;
    }

    /**
     * 检查车牌号是否存在
     * @param carNum 输入的车牌号
     * @return true 则存在
     */
    public boolean checkCarNum(String carNum){
        return  carDao.isCarExist(carNum);
    }

    /**
     * 加载指定车
     * @param carNum 车牌号
     * @return 一个车
     */
    public Car loadOneCar(String carNum) {
        Integer carId = QueryDao.getInstance().queryCarId(carNum);
        if(carId==null){
            return null;
        }
        return carDao.loadCar(String.valueOf(carId));
    }

    /**
     * 处理车删除
     * @param car 车
     */
    public void delete(Car car){
        String carId = car.getCarId();
        carDao.deleteCar(carId);
    }

    /**
     * 处理车租赁
     * @param rentData 车数据
     */
    public void rentCar(Map<String,String> rentData) {
        logService.record(rentData);
        if(rentData.get("rentType").equals("归还")){
            //若是归还则清除车辆用户
            rentData.put("userId","0");
        }
        carDao.rentCar(rentData);
    }

    /**
     * 处理车编辑
     * @param carData 车数据
     * @param isNew true为添加车
     * @return true则编辑成功
     */
    public boolean editCar(Map<String,String> carData, boolean isNew) {
        //尝试写入车数据
        return carDao.editCar(carData,isNew);
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
}