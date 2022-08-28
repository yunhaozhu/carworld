package com.zhuyunhao.www.dao;

import com.zhuyunhao.www.po.Car;
import com.zhuyunhao.www.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单例模式
 * 读取车数据类
 * @author 朱云皓
 */
public class CarDao {

    private static class daoHolder{
        private static final CarDao INSTANCE = new CarDao();
    }
    public static CarDao getInstance(){
        return CarDao.daoHolder.INSTANCE;
    }

    /**
     * 从数据库中加载所有车
     * @return car 车集合
     */
    public List<Car> loadAllCar(){
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM car;";
        ResultSet rs =JDBCUtils.executeQuery(sql, null);
        try {
            while(rs.next()){
                carList.add(setCar(rs));
            }
            return carList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    /**
     * 从数据库中加载指定车
     * @return car 车
     */
    public Car loadCar(String carId){
        List<Object> param = new ArrayList<>();
        param.add(carId);
        String sql = "SELECT * FROM car WHERE car_id = ?";
        ResultSet rs = JDBCUtils.executeQuery(sql, param);
        try {
            if(rs.next()){
                return setCar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查车牌号是否存在
     * @param carNum 车牌号
     * @return true则存在
     */
    public boolean isCarExist(String carNum) {
        List<Object> param = new ArrayList<>();
        param.add(carNum);
        String sql = "SELECT * FROM car WHERE car_num = ?";
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
     * 填充单个车数据
     * @param rs 结果集
     * @return 车
     * @throws SQLException 异常
     */
    public Car setCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getInt("car_id"));
        car.setCarNum(rs.getString("car_num"));
        car.setCarName(rs.getString("car_name"));
        car.setCarStatus(rs.getString("car_status"));
        car.setCarAge(rs.getInt("car_age"));
        car.setCarPrice(rs.getDouble("car_price"));
        car.setCarSeat(rs.getInt("car_seat"));
        car.setCarVolume(rs.getDouble("car_volume"));
        car.setCarIntroduction(rs.getString("car_introduction"));
        return car;
    }

    /**
     * 编辑数据库中车的状态
     * @param rentData   车数据
     */
    public boolean rentCar(Map<String,String> rentData){
        String sql = "UPDATE car SET car_status = ?, car_user = ? WHERE car_num = ?;";
        List<Object> param = new ArrayList<>();
        param.add(rentData.get("carStatus"));
        param.add(rentData.get("userId"));
        param.add(rentData.get("carNum"));
        return JDBCUtils.executeUpdate(sql, param) == 1;
    }

    /**
     * 编辑数据库中车的信息
     * @param carData   车数据
     * @param isNew 创建则为true
     */
    public boolean editCar(Map<String,String> carData, boolean isNew){
        String sql;
        List<Object> param = new ArrayList<>();
        param.add(carData.get("carNum"));
        param.add(carData.get("carName"));
        param.add(carData.get("carAge"));
        param.add(carData.get("carPrice"));
        param.add(carData.get("carSeat"));
        param.add(carData.get("carVolume"));
        param.add(carData.get("introduction"));
        param.add(carData.get("carStatus"));
        if(isNew){
            //若为创建
            sql = "INSERT INTO car VALUES(null,?,?,?,?,?,?,?,?,0);";
        }else{
            //若为修改,添加carId，carUser参数
            param.add(carData.get("carUser"));
            param.add(carData.get("carId"));
            sql = "UPDATE car SET car_num = ?,car_name = ?,car_age = ?,car_price = ?,car_seat = ?,car_volume = ?,car_introduction = ?,car_status=?,car_user=? WHERE car_id = ?;";
        }
        return JDBCUtils.executeUpdate(sql, param) == 1;
    }

    /**
     * 删除指定车
     * @param carId 车id
     */
    public void deleteCar(String carId){
        List<Object> param = new ArrayList<>();
        String sql = "DELETE FROM car where car_id = ?;";
        param.add(carId);
        JDBCUtils.executeUpdate(sql, param);
    }
}