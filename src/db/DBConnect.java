package db;

import java.sql.*;

/**
 * Created by shihai.li@hand-china.com on 2018/8/12.
 *  数据库jdbc连接类
 */
public class DBConnect {

    /**
     * 一个特定的连接（会话）
     * Description：创建一个数据库连接对象
     * Author:@shihai.li@hand-china.com
     */
    public static Connection connection = null;

    /**
     * 预编译SQL语句的对象
     * Description：创建预编译语句对象，PreparedStatement 继承了Statement
     * 1.多次sql执行时效率高（缓存）
     * 2.防止sql注入
     * Author:@shihai.li@hand-china.com
     */
    public static PreparedStatement ps = null;

    public static Statement st = null;

    /**
     * *表中表示数据库结果集的数据，
     * *通常是通过执行查询数据库的语句生成的
     * Description：创建一个结果集对象
     * Author:@shihai.li@hand-china.com
     */
    public static ResultSet rs = null;


    /**
     * Description：获取数据库连接方法,此方法不进行异常处理
     * Author:@shihai.li@hand-china.com
     */
    public void DBConnection(String dbType, String url, String userName, String password) throws ClassNotFoundException, SQLException {

        final  String driver;
        // switch() jdk 1.6 不支持string 类型
        switch (dbType.toLowerCase()){
            case "oracle" :
                driver = "oracle.jdbc.OracleDriver";
                break;
            case "mysql" :
                driver = "com.mysql.jdbc.Driver";
                break;
            case "sqlserver" :
                driver = "com.microsoft.jdbc.sqlserver.SQLServer-Driver";
                break;
            default: driver = "oracle.jdbc.SqlServerDriver";
        }

        Class.forName(driver); //找到oracle驱动器所在的类
        // 驱动程序管理类 DriverManager
        connection = DriverManager.getConnection(url, userName, password);
    }

    public void close(){
        try {
            rs.close();
            if(st != null){
                st.close();
            }
            if(ps != null){
                ps.close();
            }
            connection.close();
        }catch (SQLException e3){
            e3.printStackTrace();
        }
    }
    /**
     * Description：查询所有记录
     * Author:@shihai.li@hand-china.com
     */
    public ResultSet selectAll(String tableName)throws SQLException{

        if(connection != null){
            String sql = "SELECT * FROM "+tableName;
            st = connection.createStatement();
            // executeQuery sql查询方法
            rs = st.executeQuery(sql);
        }
        return rs;
    }

    public ResultSet select(String tableName, String key, String condition)throws SQLException{

        if(connection != null){
            String sql = "SELECT * FROM sys_user WHERE user_id = ?";
            // prepareStatement 无法将sql关键词作为参数
            ps = connection.prepareStatement(sql);
            //ps.setString(1, tableName);
            //ps.setString(1, key);
            ps.setString(1, condition);
            rs = ps.executeQuery();
        }
        return rs;
    }
    public ResultSet delete(String tableName, String condition)throws SQLException{

        if(connection != null){
            String sql = "DELETE  FROM "+tableName+"where"+condition;
            ps = connection.prepareStatement(sql);
            rs = st.executeQuery(sql);
        }
        return rs;
    }

}
