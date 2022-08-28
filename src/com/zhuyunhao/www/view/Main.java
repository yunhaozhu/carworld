package com.zhuyunhao.www.view;

import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.view.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * 程序入口
 * 初始化界面，调出二级界面
 * @author 朱云皓
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        initRootLayout();
        showLogin();
    }
    /**
     * 初始化RootLayout
     */
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用StageUtil工具类
     * 调出一级界面
     */
    public void showLogin(){
        this.primaryStage.setTitle("登录");
        StageUtil.showPrimaryStage("user/Login.fxml",rootLayout,this);
    }

    public void showRegister () {
        this.primaryStage.setTitle("注册");
        StageUtil.showPrimaryStage("user/Register.fxml",rootLayout,this);
    }

    public void showUserHall () {
        this.primaryStage.setTitle("Car World");
        StageUtil.showPrimaryStage("user/UserHall.fxml",rootLayout,this);
    }

    /**
     * 使用StageUtil工具类
     * 调出二级界面
     */
    public void showCarOv (){
        DialogController controller = new CarOvController();
        StageUtil.showDialogStage("car/CarOverview.fxml","车辆一览",primaryStage, controller,this);
    }

    public void showCarEdit (Stage stage){
        DialogController controller = new CarEditController();
        StageUtil.showDialogStage("car/CarEdit.fxml","填写车辆信息", stage, controller,this);
    }

    public void showAdminUser(){
        DialogController controller = new AdminUserController();
        StageUtil.showDialogStage("admin/AdminUser.fxml","用户管理",primaryStage, controller,this);
    }
    public void showAdminLog(){
        DialogController controller = new AdminLogController();
        StageUtil.showDialogStage("admin/AdminLog.fxml","出租记录",primaryStage, controller,this);
    }
    public void showAdminEdit (Stage stage){
        DialogController controller = new AdminUserEditController();
        StageUtil.showDialogStage("admin/AdminUserEdit.fxml","填写用户资料", stage, controller,this);
    }

    public void showPersonal (){
        DialogController controller = new PersonalController();
        StageUtil.showDialogStage("user/Personal.fxml","个人主页", primaryStage, controller,this);
    }
    public void showPersonEdit (Stage stage){
        DialogController controller = new PersonEditController();
        StageUtil.showDialogStage("user/PersonEdit.fxml","修改个人资料", stage, controller,this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
