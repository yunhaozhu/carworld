package com.zhuyunhao.www.dao;

import com.zhuyunhao.www.util.JDBCUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 单例模式
 * 查询数据类
 * @author 朱云皓
 */
public class QueryDao {

    private static class daoHolder{
        private static final QueryDao INSTANCE = new QueryDao();
    }
    public static QueryDao getInstance(){
        return QueryDao.daoHolder.INSTANCE;
    }

    /**
     * 以int查String类
     * @param id  id
     * @param sql 查询语句
     * @param target 数据列关键字
     * @return String
     */
    private String getString(int id, String sql,String target) {
        List<Object> param = new ArrayList<>();
        param.add(id);
        ResultSet rs = JDBCUtils.executeQuery(sql,param);
        try {
            if(rs.next()){
                return rs.getString(target);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用carId查询车的用户
     * @param  carId 车辆id
     * @return 用户id
     */
    public String queryCarUser(int carId){
        return getString(carId, "SELECT car_user FROM car WHERE car_id = ?;","car_user");
    }

    /**
     * 用用户Id查车牌号
     * @param  userId 用户id
     * @return 车牌号
     */
    public String queryCarNumByUserId(int userId){
        return getString(userId, "SELECT car_num FROM car WHERE car_user = ?;","car_num");
    }

    /**
     * 查询用户类型id对应的身份证号前缀
     * @param roleId 用户类型id
     * @return 身份证号前缀
     */
    public String queryCode(int roleId){
        return getString(roleId, "SELECT identity_code FROM role WHERE role_id = ?;","identity_code");
    }

    /**
     * 用用户类型id查类型名
     * @param roleId 用户类型id
     * @return 类型名称
     */
    public String queryRoleName(int roleId){
        return getString(roleId, "SELECT role_name FROM role WHERE role_id = ?;","role_name");
    }

    /**
     * 以str查Integer类
     * @param str str
     * @param sql 查询语句
     * @param target  数据列关键字
     * @return Integer
     */
    private Integer getInt(String str, String sql,String target) {
        List<Object> param = new ArrayList<>();
        param.add(str);
        ResultSet rs = JDBCUtils.executeQuery(sql,param);
        try {
            if(rs.next()){
                int result =  rs.getInt(target);
                if(result == 0){
                    return null;
                }else{
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户名查用户id
     * @param username 用户名
     * @return 用户id
     */
    public Integer queryUserIdByUsername(String username){
        return getInt(username,"SELECT id FROM userinfo WHERE username = ?;","id");
    }

    /**
     * 用车牌号查车id
     * @param carNum 车牌号
     * @return 车id
     */
    public Integer queryCarId(String carNum){
        return getInt(carNum,"SELECT car_id FROM car WHERE car_num = ? ;","car_id");
    }
}