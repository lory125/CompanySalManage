package Power;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class empDaoImpl implements empDao{

    public List<Emp> getDeptByPage(String idStr) {
        List<Emp> list1 = new ArrayList<>();
        Connection conn = null;//接连对象
        PreparedStatement ps = null;//sql语句的预编译对象
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4、创建PreparedStatement对象
        try {
            String sql = "SELECT  id,姓名,部门编号,所在部门,基本工资," +
                    "采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险  FROM emp WHERE id=?";
            ps = conn.prepareStatement(sql);
            int idInt = Integer.parseInt(idStr);
            ps.setInt(1,idInt);

            //5、执行
            rs = ps.executeQuery();
            //6、解析查询结果集
            while(rs.next()){
                Emp emp = new Emp();
                emp.setId(rs.getInt(1));//取第一个字段的值\
                emp.setName(rs.getString(2));
                emp.setNumber(rs.getInt(3));
                emp.setJob(rs.getString(4));
                emp.setSalary(rs.getInt(5));
                emp.setAllowance(rs.getInt(6));
                emp.setDay1(rs.getInt(7));
                emp.setDay1sal(rs.getInt(8));
                emp.setDay2(rs.getInt(9));
                emp.setDaysal2(rs.getInt(10));
                emp.setPersonalsal(rs.getInt(11));
                emp.setPersonalsafe(rs.getInt(12));
                list1.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭资源
            DBUtils.close(rs, ps, conn);
        }

        return list1;
    }
    public List<Emp> getPage() {
        List<Emp> list = new ArrayList<>();
        Connection conn = null;//接连对象
        PreparedStatement ps = null;//sql语句的预编译对象
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4、创建PreparedStatement对象
        try {
            String sql = "SELECT  id,姓名,部门编号,所在部门,基本工资," +
                    "采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险  FROM emp";
            ps = conn.prepareStatement(sql);

            //5、执行
            rs = ps.executeQuery();
            //6、解析查询结果集
            while(rs.next()){
                Emp emp = new Emp();
                emp.setId(rs.getInt(1));//取第一个字段的值\
                emp.setName(rs.getString(2));
                emp.setNumber(rs.getInt(3));
                emp.setJob(rs.getString(4));
                emp.setSalary(rs.getInt(5));
                emp.setAllowance(rs.getInt(6));
                emp.setDay1(rs.getInt(7));
                emp.setDay1sal(rs.getInt(8));
                emp.setDay2(rs.getInt(9));
                emp.setDaysal2(rs.getInt(10));
                emp.setPersonalsal(rs.getInt(11));
                emp.setPersonalsafe(rs.getInt(12));
                list.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭资源
            DBUtils.close(rs, ps, conn);
        }

        return list;
    }

}
