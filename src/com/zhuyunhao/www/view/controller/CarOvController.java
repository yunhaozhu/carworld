package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.po.Car;
import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.service.CarService;
import com.zhuyunhao.www.service.UserService;
import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 车列表界面控制器
 * @author 朱云皓
 */
public class CarOvController extends DialogController {

    @FXML
    private TableView<Car> carTable;                    //车表格
    @FXML
    private TableColumn<Car,String> carIdColumn;        //车id栏
    @FXML
    private TableColumn<Car,String> carNameColumn;      //车名栏
    @FXML
    private TableColumn<Car,String> statusColumn;       //车状态栏

    @FXML
    private Label titleLabel;           //页面标题
    @FXML
    private MenuButton adminBtn;        //管理菜单
    @FXML
    private Button rentBtn;             //预约按钮
    @FXML
    private Label carNum;               //车牌号
    @FXML
    private Label carNumLabel;          //车牌号
    @FXML
    private Label carIdLabel;           //车辆id
    @FXML
    private Label carNameLabel;         //车辆名称
    @FXML
    private Label carStatusLabel;       //车辆状态
    @FXML
    private Label carAgeLabel;          //车龄
    @FXML
    private Label carPriceLabel;        //出租价格
    @FXML
    private Label carSeatLabel;         //座位数
    @FXML
    private Label carVolumeLabel;       //车排量
    @FXML
    private Label carIntroLabel;        //车辆介绍

    User user = UserService.getInstance().getUser();    //当前用户

    private static Car selectedCar = null;              //存放当前选中的车

    private CarService carService = CarService.getInstance();

    /**
     * 初始化页面
     */
    @FXML
    private void initialize(){
        //若为客户，则显示预约，隐藏车牌号和管理菜单
        if(UserService.getInstance().authorize("rent_car")){
            titleLabel.setText("预约中心");
            rentBtn.setVisible(true);
            adminBtn.setVisible(false);
            carNum.setVisible(false);
            carNumLabel.setVisible(false);
        }
        carTable.setItems(carService.loadCarList());
        carIdColumn.setCellValueFactory(
                cellData ->cellData.getValue().carIdProperty());
        carNameColumn.setCellValueFactory(
                cellData ->cellData.getValue().carNameProperty());
        statusColumn.setCellValueFactory(
                cellData ->cellData.getValue().carStatusProperty());
        // 清除详细信息
        showCarDetails(null);
        //监听选择的展厅，展示详细信息
        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarDetails(newValue));
    }

    /**
     * 删除车事件
     */
    @FXML
    public void handleDeleteCar(){
        int selectIndex = carTable.getSelectionModel().getSelectedIndex();
        setSelectedCar();
        if(selectIndex>=0){
            carService.setSelectedCar(selectedCar);
            carService.delete(selectedCar);
            carTable.getItems().remove(selectIndex);
        }else{
            showMsg("删除失败","还未选中","请选择一辆车");
        }
    }

    /**
     * 处理车编辑
     */
    @FXML
    public void handleEditCar() {
        //设置选中的车
        setSelectedCar();
        if(selectedCar!=null){
            carService.setSelectedCar(selectedCar);
            getMainApp().showCarEdit(getDialogStage());
            if (UserService.getInstance().isOkClicked()) {
                initialize();
                UserService.getInstance().setOkClicked(false);
            }
        }else{
            showMsg("编辑失败","还未选中","请选择一辆车");
        }
    }

    /**
     * 处理车添加
     */
    @FXML
    public void handleAddCar(){
        selectedCar = null;
        carService.setSelectedCar(null);
        getMainApp().showCarEdit(getDialogStage());
        if(UserService.getInstance().isOkClicked()) {
            initialize();
            UserService.getInstance().setOkClicked(false);
        }
    }

    /**
     * 处理租车
     */
    @FXML
    public void rentCar() {
        //设置选中的车
        setSelectedCar();
        if(selectedCar!=null){
            //判断选中的车能否租赁
            String status = selectedCar.getCarStatus();
            if(status.equals("可租赁")){
                //查询当前用户是否还有车未还
                String carNum = QueryDao.getInstance().queryCarNumByUserId(Integer.parseInt(user.getUserId()));
                if(carNum!=null) {
                    showMsg("操作失败", "您还有车未归还哦", "车牌号：" + carNum + "，请在个人中心办理退租");
                }else{
                    //租车
                    Map<String, String> rentData = new HashMap<>();
                    rentData.put("carStatus","已出租");
                    rentData.put("carNum",selectedCar.getCarNum());
                    rentData.put("userId",user.getUserId());
                    rentData.put("userName",user.getUserName());
                    rentData.put("userPhone",user.getTelephone());
                    rentData.put("rentType","租借");
                    carService.rentCar(rentData);
                    showMsg("预约成功","您可以在个人中心办理退租手续","车辆：" + selectedCar.getCarName() + "，车牌号："+ selectedCar.getCarNum() +"，祝您旅途愉快");
                    initialize();
                }
            }else {
                showMsg("操作失败", "车辆已出租或无法出租", "请选择其他车辆");
            }
        }else{
            showMsg("操作失败","还未选中","请选择车辆");
        }
    }

    /**
     * 显示详细信息
     * @param selectedCar 选中的车
     */
    private void showCarDetails(Car selectedCar) {
        if(selectedCar != null){
            carIdLabel.setText(selectedCar.getCarId());
            carNameLabel.setText(selectedCar.getCarName());
            carStatusLabel.setText(selectedCar.getCarStatus());
            carAgeLabel.setText(selectedCar.getCarAge()+" 年");
            carPriceLabel.setText(selectedCar.getCarPrice()+" 元/天");
            carSeatLabel.setText(selectedCar.getCarSeat()+" 座");
            carVolumeLabel.setText(selectedCar.getCarVolume()+" T");
            carIntroLabel.setText(selectedCar.getCarIntroduction());
            carNumLabel.setText(selectedCar.getCarNum());
        }else{
            carIdLabel.setText(null);
            carNameLabel.setText(null);
            carStatusLabel.setText(null);
            carAgeLabel.setText(null);
            carPriceLabel.setText(null);
            carSeatLabel.setText(null);
            carVolumeLabel.setText(null);
            carIntroLabel.setText(null);
            carNumLabel.setText(null);
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

    //设置当前选中的车
    public void setSelectedCar() {
        selectedCar = carTable.getSelectionModel().getSelectedItem();
    }
}