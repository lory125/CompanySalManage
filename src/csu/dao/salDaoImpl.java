package csu.dao;

import csu.db.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class salDaoImpl implements salDao {
    List<String> colName = new ArrayList<>();

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {

            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int updateSal(Number id, String col, String data) {

        Connection conn = null;
        PreparedStatement ps = null;//sql语句的预编译对象
        String sql;
        conn = DBUtils.getConnection();
        //4、创建PreparedStatement对象
        try {
            if (isNumeric(data)) {
                sql = String.format("UPDATE emp SET %s = %s WHERE id = %s", col, data, id);
                System.out.println(sql + "*******数字      ");
            } else {
                sql = String.format("UPDATE emp SET %s = '%s' WHERE id = %s", col, data, id);
                System.out.println(sql + "*******字符      ");
            }

            ps = conn != null ? conn.prepareStatement(sql) : null;
            System.out.println(sql + "*******成功      ");
            //5、执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtils.close(null, ps, conn);
        }
        return 0;
    }

    @Override
    public Object[][] getEmpInfo(String sqlOrder) {
        
        Connection conn = null;//接连对象
        PreparedStatement ps = null;//sql语句的预编译对象
        ResultSet rs = null;
        conn = DBUtils.getConnection();
        //4、创建PreparedStatement对象
        try {
             String sql;
            if (sqlOrder == null || sqlOrder.length() == 0)
            sql = "SELECT id,姓名,所在部门,请假天数,请假扣款,加班天数,加班工资,修改时间 FROM emp ORDER BY 所在部门";
            else sql=sqlOrder;
            ps = conn.prepareStatement(sql);
            //5、执行
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Object[][]data=new Object[this.getTotalCount()][rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if(!colName.contains(rsmd.getColumnName(i)))
                    colName.add(rsmd.getColumnName(i));
            }
            //6、解析查询结果集

                for(int i=1;i<=this.getTotalCount();i++){
                    if(rs.next()) {
                        for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                            data[i - 1][j -1] = rs.getObject(j);
                        }
                    }
                }

            return data;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtils.close(rs, ps, conn);
        }

        return null;
    }

    @Override
    public String[] getColName(String sql) {
        int count = colName.size();
        String colNameR[] = new String[count];
        for (int i = 0; i < count; i++) {
            colNameR[i] = colName.get(i);
        }
        colName.clear();
        return colNameR;
    }

    @Override
    public int getTotalCount() {
        int totalCount = -1;
        Connection conn = null;//接连对象
        PreparedStatement ps = null;//sql语句的预编译对象
        ResultSet rs = null;
        conn = DBUtils.getConnection();
        //4、创建PreparedStatement对象
        try {
            String sql = "select count(id) from emp";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            totalCount = rs.getInt(1);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtils.close(rs, ps, conn);
        }
        return totalCount;
    }
}
