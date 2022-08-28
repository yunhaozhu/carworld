package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.view.Main;

/**
 * 一级界面父类控制器
 * @author 朱云皓
 */
public class PrimaryController {

    private Main mainApp;

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }

    public Main getMainApp() {
        return mainApp;
    }
}
