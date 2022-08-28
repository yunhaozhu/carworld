package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 大厅界面控制器
 * @author 朱云皓
 */
public class UserHallController extends PrimaryController {

    @FXML
    Button carOverview;
    @FXML
    Button adminUser;
    @FXML
    Button adminLog;

    /**
     * 初始化页面
     */
    @FXML
    private void initialize(){
        if(UserService.getInstance().authorize("rent_car")){
            carOverview.setText("立即预约");
            adminUser.setVisible(false);
            adminLog.setVisible(false);
        }
    }

    /**
     * 处理进入车列表事件
     */
    @FXML
    public void handleCarOv(){
        getMainApp().showCarOv();
    }

    /**
     * 处理进入个人主页事件
     */
    @FXML
    public void handlePersonal() {
        getMainApp().showPersonal();
    }

    /**
     * 处理查看出租记录事件
     */
    @FXML
    public void handleAdminLog() {
        getMainApp().showAdminLog();
    }

    /**
     * 处理进入用户管理事件
     */
    @FXML
    public void handleAdminUser() {
        getMainApp().showAdminUser();
    }
}
