package com.zhuyunhao.www.view.controller;

import com.zhuyunhao.www.dao.QueryDao;
import com.zhuyunhao.www.po.User;
import com.zhuyunhao.www.service.UserService;

import com.zhuyunhao.www.util.AlertMsg;
import com.zhuyunhao.www.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * 用户列表界面控制器
 * @author 朱云皓
 */
public class AdminUserController extends DialogController {

    @FXML
    private TextField customerField;                    //要查询的客户名
    @FXML
    private TableView<User> userTable;                  //用户表格
    @FXML
    private TableColumn<User,String> userIdColumn;      //用户id栏
    @FXML
    private TableColumn<User,String> userNameColumn;    //用户名栏
    @FXML
    private TableColumn<User,String> roleColumn;        //用户类型栏
    @FXML
    private Label userNameLabel;                        //用户名
    @FXML
    private Label identityNumLabel;                     //身份证号
    @FXML
    private Label telephoneLabel;                       //手机号
    @FXML
    private Label roleLabel;                            //用户类型
    @FXML
    private Label introductionLabel;                    //个性签名

    private User selectedUser = null;                   //存放当前选中的用户

    private UserService userService = UserService.getInstance();


    /**
     * 初始化页面
     */
    @FXML
    private void initialize(){
        userTable.setItems(userService.loadUserList());
        userIdColumn.setCellValueFactory(
                cellData ->cellData.getValue().userIdProperty());
        userNameColumn.setCellValueFactory(
                cellData ->cellData.getValue().userNameProperty());
        roleColumn.setCellValueFactory(
                cellData ->cellData.getValue().roleProperty());
        // 清除详细信息
        showUserDetails(null);
        //监听选择的用户，展示详细信息
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));
    }

    /**
     * 删除用户事件
     */
    @FXML
    public void handleDeleteUser(){
        int selectIndex = userTable.getSelectionModel().getSelectedIndex();
        if(selectIndex>=0){
            setSelectedUser();
            //将选中的用户传递给userService
            userService.setSelectedUser(selectedUser);
            userService.delete(selectedUser);   //在数据库中删除用户
            userTable.getItems().remove(selectIndex);
        }else{
            showMsg("删除失败","还未选中","请选择一名用户");
        }
    }

    /**
     * 处理用户编辑
     */
    @FXML
    public void handleUserEdit() {
        setSelectedUser();
        if(selectedUser != null){
            //将选中的用户传递给userService
            userService.setSelectedUser(selectedUser);
            getMainApp().showAdminEdit(getDialogStage());
            if(UserService.getInstance().isOkClicked()){
                //更新用户信息
                initialize();
                UserService.getInstance().setOkClicked(false);
            }
        }else{
            showMsg("编辑失败","还未选中","请选择一名用户");
        }
    }

    /**
     * 处理添加用户
     */
    @FXML
    public void handleNewUser(){
        selectedUser = null;
        userService.setSelectedUser(null);
        getMainApp().showAdminEdit(getDialogStage());
        if(UserService.getInstance().isOkClicked()) {
            //重新加载页面
            initialize();
            UserService.getInstance().setOkClicked(false);
        }
    }

    /**
     * 查询客户是否归还了车辆
     */
    @FXML
    public void searchCustomer(){
        //获取用户id并判断是否为客户
        Integer userId = QueryDao.getInstance().queryUserIdByUsername(customerField.getText());
        if(userId!=null && UserService.getInstance().authorize(userId,"rent_car")){
            //查询对应的车牌号
            String carNum = QueryDao.getInstance().queryCarNumByUserId(userId);
            if(carNum!=null){
                showMsg("未归还","该客户仍然在使用车辆","车牌号：" + carNum);
            }else{
                showMsg("未找到记录","该客户未租车或已退租","");
            }
        }else{
            showMsg("搜索失败","未找到该客户","请重新输入客户名");
        }
    }

    /**
     * 显示详细信息
     * @param selectedUser 选中的用户
     */
    private void showUserDetails(User selectedUser) {
        setSelectedUser();
        if(selectedUser != null){
            userNameLabel.setText(selectedUser.getUserName());
            identityNumLabel.setText(selectedUser.getIdentityNum());
            telephoneLabel.setText(selectedUser.getTelephone());
            roleLabel.setText(selectedUser.getRole());
            introductionLabel.setText(selectedUser.getIntroduction());
        }else{
            userNameLabel.setText(null);
            identityNumLabel.setText(null);
            telephoneLabel.setText(null);
            roleLabel.setText(null);
            introductionLabel.setText(null);;
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

    //设置当前选中的用户
    public void setSelectedUser() {
        this.selectedUser = userTable.getSelectionModel().getSelectedItem();
    }
}