package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import com.zhuyunhao.www.util.VerifyInput;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员编辑用户界面的控制器
 * @author 朱云皓
 */
public class AdminUserEditController extends DialogController {
    @FXML
    private TextField userNameField;             //用户名
    @FXML
    private PasswordField passwordField;         //密码
    @FXML
    private PasswordField pwConfirmField;        //确认密码
    @FXML
    private TextField telephoneFiled;            //手机号
    @FXML
    private TextField introductionField;         //个性签名
    @FXML
    private MenuButton choice;                   //用户类型选择

    private String roleId = null;                //选择的用户类型id

    private UserService userService = UserService.getInstance();

    //获取选择的用户
    private User selectedUser = userService.getSelectedUser();


    /**
     * 初始化输入框数据
     */
    @FXML
    private void initialize(){
        //若为修改
        if(selectedUser !=null){
            userNameField.setText(selectedUser.getUserName());
            passwordField.setText(selectedUser.getPassword());
            pwConfirmField.setText(selectedUser.getPassword());
            telephoneFiled.setText(selectedUser.getTelephone());
            introductionField.setText(selectedUser.getIntroduction());
            //禁用选择用户类型
            choice.setText(selectedUser.getRole());
            choice.setDisable(true);
        }
    }

    /**
     * 处理确定事件
     */
    @FXML
    public void handleOk() {
        boolean isNew = true;
        //若是修改则为false
        if(selectedUser !=null){
            isNew = false;
        }
        if(isInputValid(isNew)){
            UserService.getInstance().setOkClicked(true);
            getDialogStage().close();
        }
    }

    /**
     * 处理取消事件
     */
    @FXML
    public void handleCancel() {
        getDialogStage().close();
    }

    /**
     * 初步校验输入
     * @return true则修改成功
     */
    private boolean isInputValid(boolean isNew){
        //若需要修改密码，则flag为true
        boolean flag = true;
        //填写的用户数据
        Map<String,String> editUser = new HashMap<>();
        editUser.put("username",userNameField.getText());
        editUser.put("password",passwordField.getText());
        editUser.put("pwConfirm",pwConfirmField.getText());
        editUser.put("telephone",telephoneFiled.getText());
        editUser.put("introduction",introductionField.getText());
        //若是修改
        if(!isNew){
            //判断用户是否要修改密码
            if(editUser.get("password").length()==0 && editUser.get("pwConfirm").length()==0){
                flag = false;
            }
            //补充用户id
            editUser.put("userId", selectedUser.getUserId());
        }
        //校验输入
        if(isNew && roleId==null){
            showMsg("操作失败","请选择用户类型","");
        }else if(!VerifyInput.VerifyString(editUser.get("username"), 1, 20)){
            showMsg("操作失败","用户名不合法","长度限制为1~20字，请修改");
        }else if(!(!isNew && editUser.get("username").equals(selectedUser.getUserName())) && userService.checkUserName(editUser.get("username"))) {
            showMsg("操作失败", "用户名已存在", "请重新输入");
        } else if (flag && !VerifyInput.VerifyString(editUser.get("password"), 6, 30)) {
            showMsg("操作失败", "密码不合法", "请检查后重新输入");
        } else if (flag && !VerifyInput.VerifyString(editUser.get("pwConfirm"), 6, 30)) {
            showMsg("操作失败", "确认密码不合法", "请检查后重新输入");
        } else if (!editUser.get("password").equals(editUser.get("pwConfirm"))) {
            showMsg("操作失败", "两次密码输入不一致", "请检查后重新输入");
        } else if (!VerifyInput.VerifyPhone(editUser.get("telephone"))) {
            showMsg("操作失败", "手机号不合法", "请输入11位中国大陆手机号");
        } else if (!VerifyInput.VerifyString(editUser.get("introduction"), 1, 20)){
            showMsg("操作失败","个性签名不合法","长度限制为1~20字，请修改");
        }else {
            //若不需要修改密码，则填充旧的密码
            if (!flag) {
                editUser.put("password", selectedUser.getPassword());
            }
            editUser.put("roleId", roleId);
            //尝试编辑
            if(userService.adminEdit(editUser,isNew)){
                showMsg("操作成功","数据已写入","");
                return true;
            } else {
                showMsg("操作失败","功能异常","请稍后再试");
            }
        }
        return false;
    }

    /**
     * 响应选择用户类型
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