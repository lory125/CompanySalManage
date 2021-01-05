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
        Connection conn = null;//��������
        PreparedStatement ps = null;//sql����Ԥ�������
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4������PreparedStatement����
        try {
            String sql = "SELECT  id,����,���ű��,���ڲ���,��������," +
                    "��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ���  FROM emp WHERE id=?";
            ps = conn.prepareStatement(sql);
            int idInt = Integer.parseInt(idStr);
            ps.setInt(1,idInt);

            //5��ִ��
            rs = ps.executeQuery();
            //6��������ѯ�����
            while(rs.next()){
                Emp emp = new Emp();
                emp.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
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
            //�ر���Դ
            DBUtils.close(rs, ps, conn);
        }

        return list1;
    }
    public List<Emp> getPage() {
        List<Emp> list = new ArrayList<>();
        Connection conn = null;//��������
        PreparedStatement ps = null;//sql����Ԥ�������
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4������PreparedStatement����
        try {
            String sql = "SELECT  id,����,���ű��,���ڲ���,��������," +
                    "��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ���  FROM emp";
            ps = conn.prepareStatement(sql);

            //5��ִ��
            rs = ps.executeQuery();
            //6��������ѯ�����
            while(rs.next()){
                Emp emp = new Emp();
                emp.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
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
            //�ر���Դ
            DBUtils.close(rs, ps, conn);
        }

        return list;
    }

}
