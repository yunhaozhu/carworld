package com.zhuyunhao.www.util;

import com.sun.rowset.CachedRowSetImpl;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * JDBC工具类
 * @author 朱云皓
 */

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static{
        try {
            Properties pro = new Properties();
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res =  classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            pro.load(new FileReader(path));
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     * @return connection
     * @throws SQLException sql错误
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 查询数据库
     * @param sql 查询语句
     * @param param sql参数列表
     * @return CachedRowSetImpl（结果集）
     */
    public static CachedRowSetImpl executeQuery(String sql, List<Object> param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CachedRowSetImpl rowSet = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            addParam(param,pstmt);
            rs = pstmt.executeQuery();
            rowSet = new CachedRowSetImpl();
            rowSet.populate(rs);
            return rowSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            release(rs,pstmt,conn);
        }
        return rowSet;
    }

    /**
     * 更新数据表
     * @param sql 执行语句
     * @param param 参数列表
     * @return 影响行数
     */
    public static int executeUpdate(String sql, List<Object> param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            addParam(param,pstmt);
            return (pstmt.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();

        }finally{
            release(pstmt,conn);
        }
        return 0;
    }

    /**
     * 包装sql参数
     * @param param 参数列表
     * @param pstmt prepared statement
     * @throws SQLException sql错误
     */
    private static void addParam(List<Object> param, PreparedStatement pstmt) throws SQLException {
        if(param != null){
            for (int i = 0; i < param.size(); i++) {
                pstmt.setObject(i+1,param.get(i));
            }
        }
    }

    /**
     * 关闭连接
     * @param stmt
     * @param conn
     */
    private static void close(Statement stmt, Connection conn) {
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Statement stmt, Connection conn){
        close(stmt, conn);
    }

    public static void release(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt, conn);
    }
}
