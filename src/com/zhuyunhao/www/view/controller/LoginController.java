package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.util.VerifyInput;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 登录界面控制器
 * @author 朱云皓
 */
public class LoginController extends PrimaryController {

    @FXML
    private TextField userNameField;        //用户名
    @FXML
    private PasswordField passwordField;    //密码
    @FXML
    private CheckBox rememberCheck;         //是否记住密码

    /**
     * 初始化记住的用户名和密码
     */
    @FXML
    private void initialize(){
        UserService userService = UserService.getInstance();
        Map<String, Object> remember = userService.loadRemember();
        boolean isSelected = (boolean) remember.get("isSelected");
        if(isSelected){
            rememberCheck.setSelected(true);
            userNameField.setText((String)remember.get("username"));
            passwordField.setText((String)remember.get("password"));
        }else{
            rememberCheck.setSelected(false);
        }
    }

    /**
     * 登录按钮对应的事件
     */
    @FXML
    public void handleLogin(){
        UserService userService = UserService.getInstance();
        String username = userNameField.getText();
        String password = passwordField.getText();
        //检查输入的用户名
        if(!VerifyInput.VerifyString(username,1,20)){
            showMsg("登陆失败","用户名输入不合法","请检查输入后重新登录");
        }else if(!VerifyInput.VerifyString(password,6,30)){
            showMsg("登陆失败","密码输入不合法","请检查输入后重新登录");
        }else{
            if(!userService.checkUserName(username)) {
                showMsg("登陆失败", "用户不存在", "请检查输入后重新登录");
            }else{
                if (userService.login(username,password)){
                    List<Object> userInfo = new ArrayList<>();
                    //检查有没有选择记住用户
                    if(rememberCheck.isSelected()){
                        //若勾选则存入填充数据，否则存入null
                        userInfo.add(1);
                        userInfo.add(username);
                        userInfo.add(password);
                    }else{
                        userInfo.add(0);
                        userInfo.add(null);
                        userInfo.add(null);
                    }
                    //记住填充信息并转到用户大厅
                    userService.rememberUser(userInfo);
                    getMainApp().showUserHall();
                }else{
                    showMsg("登陆失败", "密码错误", "请检查输入或重新注册");
                }
            }
        }
    }

    /**
     * 注册按钮对应的事件
     */
    @FXML
    public void handleRegister(){
        getMainApp().showRegister();
    }

    /**
     * 弹出提示信息
     * @param title 标题1
     * @param headerText 副标题
     * @param contextText 内容
     */
    public void showMsg(String title,String headerText,String contextText){
        AlertMsg msg = new AlertMsg(title,headerText,contextText);
        StageUtil.ShowAlert(getMainApp().getPrimaryStage(),msg);
    }

}
