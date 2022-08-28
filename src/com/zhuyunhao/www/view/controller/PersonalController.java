package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.service.CarService;
import com.zhuyunhao.www.service.LogService;
import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人主页控制器
 * @author 朱云皓
 */
public class PersonalController extends DialogController {

    @FXML
    private Label userNameLabelL;        //用户名（大）
    @FXML
    private Label userNameLabel;         //用户名
    @FXML
    private Label userIdLabel;           //用户id
    @FXML
    private Label telephoneLabel;        //手机号
    @FXML
    private Label roleLabel;             //角色名
    @FXML
    private Label identityNumLabel;      //身份证号
    @FXML
    private Label introductionLabel;     //个性签名
    @FXML
    private Label ageLabel;              //用户网龄
    @FXML
    private Button returnBtn;            //归还按钮

    //获取当前登陆的用户
    private User user;
    private UserService userService = UserService.getInstance();
    private CarService carService = CarService.getInstance();

    /**
     * 初始化个人主页
     */
    @FXML
    private void initialize() {
        setUser();
        showPersonData();
        //如果是客户，显示退租按钮
        if(user.getRoleId().equals("3")){
            returnBtn.setVisible(true);
        }
    }

    /**
     * 处理退租
     */
    @FXML
    private void handleReturn(){
        AlertMsg msg;
        String carNum = QueryDao.getInstance().queryCarNumByUserId(Integer.parseInt(user.getUserId()));
        if(carNum!=null){
            Map<String,String> rentData= new HashMap<>();
            rentData.put("carNum",carNum);
            rentData.put("userId",user.getUserId());
            rentData.put("userName",user.getUserName());
            rentData.put("userPhone",user.getTelephone());
            rentData.put("rentType","归还");
            rentData.put("carStatus","可租赁");
            carService.rentCar(rentData);
            msg = new AlertMsg("退租成功！","欢迎下次光临","车辆 "+ carNum + " 已经归还");
        }else{
            msg = new AlertMsg("操作失败","您还没有租车","请先在预约中心租车哦！");
        }
        StageUtil.ShowAlert(getMainApp().getPrimaryStage(), msg);
    }

    /**
     * 处理登出事件
     */
    @FXML
    private void handleLogout(){
        closeDialog();
        getMainApp().showLogin();
    }

    /**
     * 处理修改事件
     */
    @FXML
    private void handlePersonEdit(){
        getMainApp().showPersonEdit(getDialogStage());
        //若修改成功，刷新页面数据
        setUser();
        if(userService.isOkClicked()){
            initialize();
            //用户ok设为否
            userService.setOkClicked(false);
        }
    }

    /**
     * 填充页面用户数据
     */
    private void showPersonData(){
        userNameLabel.setText(user.getUserName());
        userNameLabelL.setText(user.getUserName());
        userIdLabel.setText("User ID: "+ user.getUserId());
        telephoneLabel.setText(user.getTelephone());
        introductionLabel.setText(user.getIntroduction());
        identityNumLabel.setText(user.getIdentityNum());
        roleLabel.setText(QueryDao.getInstance().queryRoleName(Integer.parseInt(user.getRoleId())));
        ageLabel.setText("加入 Car World 的第 "+userService.getUserAge()+" 天");
    }

    public void setUser() {
        this.user = userService.getUser();
    }
}
