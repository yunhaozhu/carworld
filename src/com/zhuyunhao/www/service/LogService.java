package com.zhuyunhao.www.service;

import com.zhuyunhao.www.dao.LogDao;
import com.zhuyunhao.www.po.Log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 单例模式
 * 记录业务处理
 * @author 朱云皓
 */
public class LogService {

    private static class serviceHolder{
        private static final LogService INSTANCE = new LogService();
    }
    public static LogService getInstance(){
        return LogService.serviceHolder.INSTANCE;
    }

    private LogDao logDao = LogDao.getInstance();

    /**
     * 加载记录列表
     * @return 记录
     */
    public ObservableList<Log> loadLogList() {
        ObservableList<Log> logNames = FXCollections.observableArrayList();
        List<Log> logList =  logDao.loadAllLog();
        logNames.addAll(logList);
        return logNames;
    }

    /**
     * 加载指定用户的租车记录
     * @param userName 用户名
     * @return 记录
     */
    public ObservableList<Log> loadUserLogList(String userName) {
        ObservableList<Log> logNames = FXCollections.observableArrayList();
        List<Log> logList =  logDao.loadUserLogList(userName);
        logNames.addAll(logList);
        return logNames;
    }

    /**
     * 加载指定车的出租记录
     * @param carNum 车牌号
     * @return 记录
     */
    public ObservableList<Log> loadCarLogList(String carNum) {
        ObservableList<Log> logNames = FXCollections.observableArrayList();
        List<Log> logList =  logDao.loadCarLogList(carNum);
        logNames.addAll(logList);
        return logNames;
    }

    /**
     * 自动添加记录
     * @param rentData 记录数据
     */
    public void record(Map<String, String> rentData) {
        //添加记录日期
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String day = sdf.format(date);
        rentData.put("rentDate",day);
        logDao.record(rentData);
    }

    /**
     * 处理记录删除
     * @param log 记录
     */
    public void delete(Log log){
        String logId = log.getLogId();
        logDao.deleteLog(logId);
    }
}