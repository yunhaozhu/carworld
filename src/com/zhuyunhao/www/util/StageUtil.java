package com.zhuyunhao.www.util;

import com.zhuyunhao.www.view.Main;
import com.zhuyunhao.www.view.controller.DialogController;
import com.zhuyunhao.www.view.controller.PrimaryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 与GUI有关的工具类
 * @author 朱云皓
 */
public class StageUtil {
    /**
     * 调出一级界面
     * @param source fxml文件路径
     * @param rootLayout
     * @param mainapp
     */
    public static void showPrimaryStage(String source,BorderPane rootLayout,Main mainapp){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource(source));
            AnchorPane primaryLayout = (AnchorPane) loader.load();

            rootLayout.setCenter(primaryLayout);
            //加载控制器
            PrimaryController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调出二级界面
     * @param source fxml文件路径
     * @param title 二级界面title
     * @param primaryStage 父Stage
     * @param controller fxml对应的控制器
     * @param mainApp
     */
    public static void showDialogStage(String source, String title, Stage primaryStage, DialogController controller, Main mainApp){

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource(source));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //加载控制器
            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainApp);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹出提示窗口
     * @param stage 父stage
     * @param msg 提示信息
     */
    public static void ShowAlert(Stage stage,AlertMsg msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(stage);
        alert.setTitle(msg.getTitle());
        alert.setHeaderText(msg.getHeaderText());
        alert.setContentText(msg.getContentText());
        alert.showAndWait();
    }
}
