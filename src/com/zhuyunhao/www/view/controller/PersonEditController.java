package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.util.VerifyInput;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改个人资料界面控制器
 * @author 朱云皓
 */
public class PersonEditController extends DialogController {
    @FXML
    private TextField userNameField;        //用户名
    @FXML
    private TextField telephoneFiled;       //手机号
    @FXML
    private TextField introductionField;    //个性签名
    @FXML
    private PasswordField passwordField;    //密码
    @FXML
    private PasswordField pwConfirmField;   //确认密码

    private UserService userService = UserService.getInstance();
    //获取当前登陆的用户
    private User user = userService.getUser();

    /**
     * 初始化个人信息
     */
    @FXML
    private void initialize(){
        userNameField.setText(user.getUserName());
        telephoneFiled.setText(user.getTelephone());
        introductionField.setText(user.getIntroduction());
    }

    /**
     * 处理修改事件
     */
    @FXML
    private void handleOK(){
        if(isInputValid()){
            //若数据库修改成功，更新用户ok数据
            userService.setOkClicked(true);
            getDialogStage().close();
        }
    }

    @FXML
    private void handleCancel(){
        getDialogStage().close();
    }

    /**
     * 校验输入
     * 尝试修改数据
     */
    private boolean isInputValid(){
        //若用户需要修改密码，则flag为true
        boolean flag = true;
        Map<String,String> editUser = new HashMap<>();
        editUser.put("username",userNameField.getText());
        editUser.put("password",passwordField.getText());
        editUser.put("pwConfirm",pwConfirmField.getText());
        editUser.put("telephone",telephoneFiled.getText());
        editUser.put("introduction",introductionField.getText());
        //判断用户是否要修改密码
        if(editUser.get("password").length()==0 && editUser.get("pwConfirm").length()==0){
            flag = false;
        }
        if(!VerifyInput.VerifyString(editUser.get("username"), 1, 20)){
            showMsg("修改失败","用户名不合法","长度限制为1~20字，请修改");
        } else if (!VerifyInput.VerifyPhone(editUser.get("telephone"))){
            showMsg("修改失败","手机号不合法","请输入11位中国大陆手机号");
        } else if (!VerifyInput.VerifyString(editUser.get("introduction"), 1, 20)){
            showMsg("修改失败","个性签名不合法","长度限制为1~20字，请修改");
        } else if (flag && !VerifyInput.VerifyString(editUser.get("password"), 6, 30)) {
            showMsg("注册失败", "密码不合法", "请检查后重新输入");
        } else if (flag && !VerifyInput.VerifyString(editUser.get("pwConfirm"), 6, 30)) {
            showMsg("注册失败", "确认密码不合法", "请检查后重新输入");
        } else if (!editUser.get("password").equals(editUser.get("pwConfirm"))) {
            showMsg("注册失败", "两次密码输入不一致", "请检查后重新输入");
        } else {
            //若不需要修改密码，则填充旧的密码
            if (!flag) {
                editUser.put("password", user.getPassword());
            }
            //若要修改用户名，检查用户名是否已存在
            if (!editUser.get("username").equals(user.getUserName()) && userService.checkUserName(editUser.get("username"))) {
                showMsg("注册失败", "用户已存在", "请更换用户名");
            } else {
                if(userService.edit(editUser)){
                    return true;
                }else{
                    showMsg("修改失败","功能异常","请稍后再试");
                }
            }
        }
        return false;
    }

    /**
     * 弹出提示信息
     * @param title 标题
     * @param headerText 副标题
     * @param contextText 内容
     */
    public void showMsg (String title, String headerText, String contextText){
        AlertMsg msg = new AlertMsg(title, headerText, contextText);
        StageUtil.ShowAlert(getDialogStage(), msg);
    }
}
