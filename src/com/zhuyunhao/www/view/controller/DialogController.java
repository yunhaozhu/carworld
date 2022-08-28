package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.view.Main;
import javafx.stage.Stage;

/**
 * 二级界面父类控制器
 * @author 朱云皓
 */
public class DialogController {
    private Main mainApp;
    private Stage dialogStage;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Main getMainApp() {
        return mainApp;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void closeDialog() {
        dialogStage.close();
    }

}
