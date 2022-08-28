package com.zhuyunhao.www.util;

/**
 * 创建提示弹窗类
 * @author 朱云皓
 */
public class AlertMsg {
    private String title;           //提示标题
    private String HeaderText;      //副标题
    private String ContentText;     //内容

    public AlertMsg(String title, String headerText, String contentText) {
        this.title = title;
        HeaderText = headerText;
        ContentText = contentText;
    }

    public String getTitle() {
        return title;
    }

    public String getHeaderText() {
        return HeaderText;
    }

    public String getContentText() {
        return ContentText;
    }


}
