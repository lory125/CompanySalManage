package SpeWindow.db;


import password.ConnectStr;

import java.sql.*;

public class DBUtils {

    //获得连接
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/company?useUnicode=true&autoReconnect=true&characterEncoding=utf-8";
        Connection conn = DriverManager.getConnection(url,"root", ConnectStr.connectPassword);
        return conn;
    }
    //关闭资源
    public static void close(ResultSet rs, Statement stmt,Connection conn)  {

        //7.关闭资源
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
