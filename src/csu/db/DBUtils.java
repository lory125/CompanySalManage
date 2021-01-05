package csu.db;

import java.sql.*;

public class DBUtils {

//	private static ComboPooledDataSource dataSouce =
//			new ComboPooledDataSource();

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {

        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2 、定义url
            String url = "jdbc:mysql://localhost:3306/company?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";
            String user = "root";
            String pwd = "password";

            //3、创建连接
            Connection conn = DriverManager.getConnection(url, user, pwd);
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 关闭资源
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt,
                             Connection conn) {
        //7、关闭资源
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
