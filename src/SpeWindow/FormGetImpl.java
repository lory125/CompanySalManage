package SpeWindow;

import SpeWindow.db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormGetImpl implements FormGet{
    @Override
    public List<Dept> getDeptByPage() {
        List<Dept> list = new ArrayList<>();
        Connection conn = null;//接连对象
        PreparedStatement ps = null;//sql语句的预编译对象
        ResultSet rs = null;

        //4、创建PreparedStatement对象
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp; ";
            ps = conn.prepareStatement(sql);
            //5、执行
            rs = ps.executeQuery();
            //6、解析查询结果集
            while(rs.next()){
                Dept dept = new Dept();
                dept.setId(rs.getInt(1));//取第一个字段的值\
                dept.setName(rs.getString(2));
                dept.setNumber(rs.getInt(3));
                dept.setJob(rs.getString(4));
                dept.setSalary(rs.getInt(5));
                dept.setAllowance(rs.getInt(6));
                dept.setDay1(rs.getInt(7));
                dept.setDay1sal(rs.getInt(8));
                dept.setDay2(rs.getInt(9));
                dept.setDaysal2(rs.getInt(10));
                dept.setPersonalsal(rs.getInt(11));
                dept.setPersonalsafe(rs.getInt(12));
                list.add(dept);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            //关闭资源
            DBUtils.close(rs, ps, conn);
        }

        return list;
    }

}
