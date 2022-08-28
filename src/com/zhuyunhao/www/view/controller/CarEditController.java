package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.po.Car;
import com.zhuyunhao.www.service.CarService;
import com.zhuyunhao.www.service.UserService;


import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.util.VerifyInput;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

/**
 * 编辑车界面的控制器
 * @author 朱云皓
 */
public class CarEditController extends DialogController {
    @FXML
    private TextField carNumField;          //车牌号
    @FXML
    private TextField carNameField;         //车辆名称
    @FXML
    private TextField carAgeField;          //车龄
    @FXML
    private TextField carPriceField;        //出租价格
    @FXML
    private TextField carSeatField;         //座位数
    @FXML
    private TextField carVolumeField;       //车排量
    @FXML
    private TextField carIntroField;        //车辆介绍

    private CarService carService = CarService.getInstance();

    //获取当前选定的车
    private Car selectedCar = carService.getSelectedCar();

    /**
     * 初始化输入框数据
     */
    @FXML
    private void initialize(){
        if(selectedCar!=null){
            carNumField.setText(selectedCar.getCarNum());
            carNameField.setText(selectedCar.getCarName());
            carAgeField.setText(selectedCar.getCarAge());
            carPriceField.setText(selectedCar.getCarPrice());
            carSeatField.setText(selectedCar.getCarSeat());
            carVolumeField.setText(selectedCar.getCarVolume());
            carIntroField.setText(selectedCar.getCarIntroduction());
            carNumField.setDisable(true);
        }
    }

    /**
     * 处理确定事件
     */
    @FXML
    public void handleOk() {
        boolean isNew = true;
        //若是修改则为false
        if(selectedCar!=null){
            isNew = false;
        }
        if(isInputValid(isNew)){
            UserService.getInstance().setOkClicked(true);
            getDialogStage().close();
        }
    }

    /**
     * 处理取消事件
     */
    @FXML
    public void handleCancel() {
        getDialogStage().close();
    }

    /**
     * 校验输入，尝试修改
     * @return true则成功
     */
    private boolean isInputValid(boolean isNew){

        //填写的车数据
        Map<String,String> editCar = new HashMap<>();
        editCar.put("carNum",carNumField.getText());
        editCar.put("carName",carNameField.getText());
        editCar.put("carStatus","可租赁");
        editCar.put("carAge",carAgeField.getText());
        editCar.put("carPrice",carPriceField.getText());
        editCar.put("carSeat", carSeatField.getText());
        editCar.put("carVolume", carVolumeField.getText());
        editCar.put("introduction", carIntroField.getText());
        //若是修改
        if(!isNew){
            //补充车id，状态和车的使用者
            editCar.put("carId",selectedCar.getCarId());
            editCar.put("carStatus",selectedCar.getCarStatus());
            editCar.put("carUser",QueryDao.getInstance().queryCarUser(Integer.parseInt(selectedCar.getCarId())));
        }
        //校验输入
        if(!VerifyInput.VerifyString(editCar.get("carName"), 1, 20)){
            showMsg("操作失败","车辆名称不合法","长度限制为1~20字，请修改");
        }else if(!VerifyInput.VerifyString(editCar.get("carNum"),7,8)){
            showMsg("操作失败","车牌号不合法","长度限制为7~8字，请修改");
        }else if (!(!isNew && editCar.get("carNum").equals(selectedCar.getCarNum())) && carService.checkCarNum(editCar.get("carNum"))) {
            showMsg("操作失败", "车牌号已存在", "请重新输入");
        }else if(!VerifyInput.VerifyInt(editCar.get("carAge"))){
            showMsg("操作失败","车辆年龄不合法","请输入正整数");
        }else if(!VerifyInput.VerifyFloat(editCar.get("carPrice"))){
            showMsg("操作失败","车辆价格不合法","请检查后重新输入");
        }else if(!VerifyInput.VerifyInt(editCar.get("carSeat"))){
            showMsg("操作失败","座位数不合法","请检查后重新输入");
        }else if(!VerifyInput.VerifyFloat(editCar.get("carVolume"))){
            showMsg("操作失败","车排量不合法","请检查后重新输入");
        }else if(!VerifyInput.VerifyString(editCar.get("introduction"),1,20)){
            showMsg("操作失败","车辆介绍不合法","长度限制为1~20字，请修改");
        }else{
            if(carService.editCar(editCar,isNew)){
                showMsg("操作成功","数据已写入","");
                return true;
            } else {
                showMsg("操作失败", "功能异常", "请稍后再试");
            }
        }
        return false;
    }

    /**
     * 弹出提示信息
     * @param title 标题
     * @param headerText 副标题
     * @param contextText 内容
     */
    public void showMsg (String title, String headerText, String contextText){
        AlertMsg msg = new AlertMsg(title, headerText, contextText);
        StageUtil.ShowAlert(getDialogStage(), msg);
    }
}