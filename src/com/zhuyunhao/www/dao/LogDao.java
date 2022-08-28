package com.zhuyunhao.www.dao;

import com.zhuyunhao.www.po.Log;
import com.zhuyunhao.www.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单例模式
 * 读取记录数据类
 * @author 朱云皓
 */
public class LogDao {

    private static class daoHolder{
        private static final LogDao INSTANCE = new LogDao();
    }
    public static LogDao getInstance(){
        return LogDao.daoHolder.INSTANCE;
    }

    /**
     * 从数据库中加载所有租车记录
     * @return log 记录集合
     */
    public List<Log> loadAllLog(){
        String sql = "SELECT * FROM log;";
        List<Log> logList = new ArrayList<>();
        List<Object> param = new ArrayList<>();
        ResultSet rs =JDBCUtils.executeQuery(sql, param);
        try {
            while(rs.next()){
                logList.add(setLog(rs));
            }
            return logList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    /**
     * 从数据库中加载所有租车记录
     * @return log 记录名集合
     */
    public List<Log> loadUserLogList(String userName){
        String sql = "SELECT * FROM log WHERE user_name = ?;";
        List<Log> logList = new ArrayList<>();
        List<Object> param = new ArrayList<>();
        param.add(userName);
        ResultSet rs =JDBCUtils.executeQuery(sql, param);
        try {
            while(rs.next()){
                logList.add(setLog(rs));
            }
            return logList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    /**
     * 从数据库中加载所有租车记录
     * @return log 记录名集合
     */
    public List<Log> loadCarLogList(String carNum){
        String sql = "SELECT * FROM log WHERE car_num = ?;";
        List<Log> logList = new ArrayList<>();
        List<Object> param = new ArrayList<>();
        param.add(carNum);
        ResultSet rs =JDBCUtils.executeQuery(sql, param);
        try {
            while(rs.next()){
                logList.add(setLog(rs));
            }
            return logList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    /**
     * 自动添加记录
     * @param rentData 租车数据
     */
    public void record(Map<String, String> rentData) {
        String sql = "INSERT INTO log VALUES(null,?,?,?,?,?,?);";
        List<Object> param = new ArrayList<>();
        param.add(rentData.get("carNum"));
        param.add(rentData.get("userId"));
        param.add(rentData.get("userName"));
        param.add(rentData.get("userPhone"));
        param.add(rentData.get("rentDate"));
        param.add(rentData.get("rentType"));
        JDBCUtils.executeUpdate(sql, param);
    }

    /**
     * 填充单个记录数据
     * @param rs 结果集
     * @return 一个记录
     * @throws SQLException 异常
     */
    public Log setLog(ResultSet rs) throws SQLException {
        Log log = new Log();
        log.setLogId(rs.getInt("log_id"));
        log.setCarNum(rs.getString("car_num"));
        log.setUserId(rs.getString("user_id"));
        log.setUserName(rs.getString("user_name"));
        log.setUserPhone(rs.getString("user_phone"));
        log.setDate(rs.getString("date"));
        log.setType(rs.getString("type"));
        return log;
    }

    /**
     * 删除指定记录
     * @param logId 记录id
     */
    public void deleteLog(String logId){
        List<Object> param = new ArrayList<>();
        String sql = "DELETE FROM log WHERE log_id = ?";
        param.add(logId);
        JDBCUtils.executeUpdate(sql, param);
    }
}