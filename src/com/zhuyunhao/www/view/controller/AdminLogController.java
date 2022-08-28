package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.po.Log;
import com.zhuyunhao.www.po.Car;
import com.zhuyunhao.www.service.LogService;
import com.zhuyunhao.www.service.CarService;
import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * 出租记录页控制器
 * @author 朱云皓
 */
public class AdminLogController extends DialogController {

    @FXML
    private TableView<Log> logTable;                //记录表格
    @FXML
    private TableColumn<Log,String> dateColumn;     //记录日期
    @FXML
    private TableColumn<Log,String> typeColumn;     //记录类型
    @FXML
    private TableColumn<Log,String> carNumColumn;   //车牌号
    @FXML
    private Label logIdLabel;                       //记录id
    @FXML
    private Label userIdLabel;                      //用户id
    @FXML
    private Label userNameLabel;                    //用户名
    @FXML
    private Label userPhoneLabel;                   //手机号
    @FXML
    private Label dateLabel;                        //记录日期
    @FXML
    private Label logTypeLabel;                     //记录类型

    private Log selectedLog = null;                 //存放当前选中的记录

    private LogService logService = LogService.getInstance();

    @FXML
    private Label carIdLabel;                       //车辆id
    @FXML
    private Label carNumLabel;                      //车牌号
    @FXML
    private Label carNameLabel;                     //车辆名称
    @FXML
    private Label carAgeLabel;                      //车龄
    @FXML
    private Label carPriceLabel;                    //出租价格
    @FXML
    private Label carSeatLabel;                     //座位数
    @FXML
    private Label carVolumeLabel;                   //车排量
    @FXML
    private Label carIntroLabel;                    //车辆介绍
    @FXML
    private TextField searchField ;                 //记录搜索框

    private Car car = null;                                //与记录对应的车

    private CarService carService = CarService.getInstance();

    /**
     * 初始化页面
     */
    @FXML
    private void initialize(){
        logTable.setItems(logService.loadLogList());
        dateColumn.setCellValueFactory(
                cellData ->cellData.getValue().dateProperty());
        typeColumn.setCellValueFactory(
                cellData ->cellData.getValue().typeProperty());
        carNumColumn.setCellValueFactory(
                cellData ->cellData.getValue().carNumProperty());
        //监听选择的记录，展示详细信息
        logTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        //清除记录的详细信息
        showDetails(null);
    }

    /**
     * 用客户名搜索记录
     */
    @FXML
    public void handleSearchByName(){
        if(searchField.getText().length()>0){
            logTable.setItems(logService.loadUserLogList(searchField.getText()));
        }else{
            showMsg("无法搜索","请输入客户名称","");
        }
    }

    /**
     * 用车牌号搜索记录
     */
    @FXML
    public void handleSearchByCar(){
        if(searchField.getText().length()>0){
            logTable.setItems(logService.loadCarLogList(searchField.getText()));
        }else{
            showMsg("无法搜索","请输入车牌号","");
        }
    }

    /**
     * 关闭搜索
     */
    @FXML
    public void handleCloseSearch(){
        initialize();
    }

    /**
     * 删除记录
     */
    @FXML
    public void handleDeleteLog(){
        int selectIndex = logTable.getSelectionModel().getSelectedIndex();
        setSelectedLog();
        if(selectIndex>=0){
            logService.delete(selectedLog);
            logTable.getItems().remove(selectIndex);
        }else{
            showMsg("删除失败","还未选中","请选择一条记录");
        }
    }

    /**
     * 处理车辆编辑和添加
     */
    @FXML
    public void handleCarEdit() {
        carService.setSelectedCar(car);
        getMainApp().showCarEdit(getDialogStage());
        if(UserService.getInstance().isOkClicked()){
            initialize();   //重新加载页面
            UserService.getInstance().setOkClicked(false);
        }
    }

    /**
     * 填充页面信息
     */
    private void showDetails(Log selectedLog) {
        if(selectedLog != null){
            logIdLabel.setText(selectedLog.getLogId());
            userIdLabel.setText(selectedLog.getUserId());
            userNameLabel.setText(selectedLog.getUserName());
            userPhoneLabel.setText(selectedLog.getUserPhone());
            dateLabel.setText(selectedLog.getDate());
            logTypeLabel.setText(selectedLog.getDate());
            if(setCar(selectedLog)){
                carIdLabel.setText(car.getCarId());
                carNumLabel.setText(car.getCarNum());
                carNameLabel.setText(car.getCarName());
                carAgeLabel.setText(car.getCarAge());
                carPriceLabel.setText(car.getCarPrice());
                carSeatLabel.setText(car.getCarSeat());
                carVolumeLabel.setText(car.getCarVolume());
                carIntroLabel.setText(car.getCarIntroduction());
            }else{
                carIdLabel.setText("车辆已删除");
                carNumLabel.setText(null);
                carNameLabel.setText(null);
                carAgeLabel.setText(null);
                carPriceLabel.setText(null);
                carSeatLabel.setText(null);
                carVolumeLabel.setText(null);
                carIntroLabel.setText(null);
            }
        }else{
            logIdLabel.setText(null);
            userIdLabel.setText(null);
            userNameLabel.setText(null);
            userPhoneLabel.setText(null);
            dateLabel.setText(null);
            logTypeLabel.setText(null);
            carIdLabel.setText(null);
            carNumLabel.setText(null);
            carNameLabel.setText(null);
            carAgeLabel.setText(null);
            carPriceLabel.setText(null);
            carSeatLabel.setText(null);
            carVolumeLabel.setText(null);
            carIntroLabel.setText(null);
        }
    }

    /**
     * 弹出提示信息
     * @param title 标题
     * @param headerText 副标题
     * @param contextText 内容
     */
    public void showMsg (String title, String headerText, String contextText){
        AlertMsg msg = new AlertMsg(title, headerText, contextText);
        StageUtil.ShowAlert(getMainApp().getPrimaryStage(), msg);
    }

    //设置当前选中的记录
    public void setSelectedLog() {
        selectedLog = logTable.getSelectionModel().getSelectedItem();
    }

    //设置与记录对应的车
    public boolean setCar(Log selectedLog) {
        this.car = carService.loadOneCar(selectedLog.getCarNum());
        return car!=null;
    }
}