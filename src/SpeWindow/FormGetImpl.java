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
        Connection conn = null;//��������
        PreparedStatement ps = null;//sql����Ԥ�������
        ResultSet rs = null;

        //4������PreparedStatement����
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp; ";
            ps = conn.prepareStatement(sql);
            //5��ִ��
            rs = ps.executeQuery();
            //6��������ѯ�����
            while(rs.next()){
                Dept dept = new Dept();
                dept.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
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
            //�ر���Դ
            DBUtils.close(rs, ps, conn);
        }

        return list;
    }

}
