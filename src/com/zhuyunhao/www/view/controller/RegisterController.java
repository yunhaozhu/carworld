package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.util.VerifyInput;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册界面控制器
 * @author 朱云皓
 */
public class RegisterController extends PrimaryController {

    @FXML
    private TextField userNameField;                //用户名
    @FXML
    private PasswordField passwordField;            //密码
    @FXML
    private PasswordField pwConfirmField;           //确认密码
    @FXML
    private TextField telephoneField;               //手机号
    @FXML
    private MenuButton choice;                      //用户类型选择

    private String roleId = null;                   //选择的用户类型

    /**
     * 处理取消事件
     */
    @FXML
    public void handleCancel() {
        getMainApp().showLogin();
    }   //回到登陆页

    /**
     * 处理登录事件
     * 初步判断输入
     */
    @FXML
    public void handleRegister() {
        Map<String,String> registerData = new HashMap<>();
        registerData.put("username",userNameField.getText());
        registerData.put("password",passwordField.getText());
        registerData.put("pwConfirm",pwConfirmField.getText());
        registerData.put("telephone",telephoneField.getText());
        registerData.put("introduction","用户很懒，还没有填写哦~");

        //验证输入有效性
        if (!VerifyInput.VerifyString(registerData.get("username"), 1, 20)) {
            showMsg("注册失败", "用户名不合法", "请检查后重新输入");
        } else if (!VerifyInput.VerifyPhone(registerData.get("telephone"))) {
            showMsg("注册失败", "手机号不合法", "请输入11位中国大陆手机号");
        } else if (!VerifyInput.VerifyString(registerData.get("password"), 6, 30)) {
            showMsg("注册失败", "密码不合法", "请检查后重新输入");
        } else if (!VerifyInput.VerifyString(registerData.get("pwConfirm"), 6, 30)) {
            showMsg("注册失败", "确认密码不合法", "请检查后重新输入");
        } else if (!registerData.get("password").equals(registerData.get("pwConfirm"))) {
            showMsg("注册失败", "两次密码输入不一致", "请检查后重新输入");
        } else if (roleId == null) {
            showMsg("注册失败", "未选择角色", "请选择角色后重新注册");
        } else {
            UserService userService = UserService.getInstance();
            //检查用户名是否已存在
            if (userService.checkUserName(registerData.get("username"))) {
                showMsg("注册失败", "用户已存在", "请更换用户名");
            } else{
                registerData.put("roleId", roleId);
                boolean result = userService.register(registerData);
                if(result){
                    List<Object> userInfo = new ArrayList<>();
                    //记住填充信息并转到登陆页面
                    userInfo.add(1);
                    userInfo.add(registerData.get("username"));
                    userInfo.add(registerData.get("password"));
                    userService.rememberUser(userInfo);
                    getMainApp().showLogin();
                    showMsg("注册成功", "欢迎来到Car World", "现在可以登录了");
                }else{
                    showMsg("注册失败", "注册功能异常", "请尝试稍后重新注册");
                }
            }
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

    /**
     * 响应选择角色按钮
     * 设置roleId
     */
    public void setRoleId(String roleName){
        switch(roleName){
            case "老板":
                this.roleId = "1";
                break;
            case "管理员":
                this.roleId = "2";
                break;
            case "客户":
                this.roleId = "3";
                break;
        }
        this.choice.setText(roleName);
    }
    @FXML
    public void setBoss () {
        setRoleId("老板");
    }
    @FXML
    public void setAdmin () {
        setRoleId("管理员");
    }
    @FXML
    public void setCustomer () {
        setRoleId("客户");
    }
}
