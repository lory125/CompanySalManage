package SpeWindow;

import SpeWindow.db.DBUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormPanel extends JPanel{

    FormGet formGet = new FormGetImpl();

    JScrollPane scrollPane;
    JTable table;


    public FormPanel() {

        setLayout(new BorderLayout());
        String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
        List<Dept> deptList = formGet.getDeptByPage();
        Object[][] formdata = getFormData(deptList);
        DefaultTableModel model = new DefaultTableModel(formdata,Names);
        table = new JTable();
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        /*****/

//        //�����������
//        List<Dept> deptList = formGet.getDeptByPage();
//        Object[][] formdata = getFormData(deptList);
//
//        //�����ͷ
//        String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
//        //����
//        table = new JTable(formdata, Names);
//
//        //����ѡ������ɫ
//        table.setSelectionBackground(Color.yellow);
//        table.setSelectionForeground(Color.RED);
//        // �������뵽�����������
//        JScrollPane scrollPane = new JScrollPane(table);
////        getContentPane().add(scrollPane, BorderLayout.CENTER);
//        add(scrollPane, BorderLayout.CENTER);
        //��ӹ��ܰ���
        JLabel label = new JLabel("��ѯ��ʽ(��������Ϊ��ֵ����1000)");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("������");
        deptBox.addItem("��������");
        deptBox.addItem("����������");
        JTextField textArea = new JTextField(10);
        JButton Btn1 = new JButton("��ѯ");
        JButton Btn2 = new JButton("ɾ��");
        JButton Btn3 = new JButton("�����ϵ�");
        JButton Btn4 = new JButton("�����µ�");
        JButton Btn5 = new JButton("���Ӳ���");
        JButton Btn6 = new JButton("���ٲ���");

        //��������
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                List<Dept> list = new ArrayList<>();
                Connection conn = null;//��������
                PreparedStatement ps = null;//sql����Ԥ�������
                ResultSet rs = null;


                try {
                    conn = DBUtils.getConnection();

                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textArea.getText());
                    } else if ((String) deptBox.getSelectedItem() == "��������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textArea.getText());
                    } else {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� " +
                                "FROM emp WHERE �������� between  ?-1000 and ?+1000";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(textArea.getText()));
                        ps.setInt(2, Integer.parseInt(textArea.getText()));
                    }


                    //5��ִ��
                    rs = ps.executeQuery();

                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

//                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
//                    //����
//                    DefaultTableModel model = new DefaultTableModel(formdata,Names);
//                    table = new JTable(formdata, Names);
//                    // �������뵽�����������
//                    JScrollPane scrollPane = new JScrollPane(table);
////                    getContentPane().add(scrollPane, BorderLayout.CENTER);
//                    add(scrollPane, BorderLayout.CENTER);
//                    setVisible(true);
                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //ɾ��
        Btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "DELETE FROM emp WHERE ���ڲ��� = ?";
                    } else {
                        sql = "DELETE FROM emp WHERE ���� = ?";
                    }

                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());

                    //5��ִ��
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                reFresh();
            }
        });

        //�ϵ�����
        Btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql1 = "UPDATE emp SET �������� = ��������*1.1 WHERE ���ڲ��� =?";
                    } else {
                        sql1 = "UPDATE emp SET �������� = ��������*1.1 WHERE ���� =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //����
                    ps.executeUpdate();

                    //��ѯ
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                    } else {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //�µ�����
        Btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql1 = "UPDATE emp SET �������� = ��������*0.9 WHERE ���ڲ��� =?";
                    } else {
                        sql1 = "UPDATE emp SET �������� = ��������*0.9 WHERE ���� =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //����
                    ps.executeUpdate();

                    //��ѯ
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                    } else {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //�����Ը��˺Ͳ���
        Btn5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql1 = "UPDATE emp SET ��ů���� = ��ů����+100 WHERE ���ڲ��� =?";
                    } else {
                        sql1 = "UPDATE emp SET ��ů���� = ��ů����+80 WHERE ���� =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //����
                    ps.executeUpdate();

                    //��ѯ
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                    } else {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Btn6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql1 = "UPDATE emp SET ��ů���� = ��ů����-80 WHERE ���ڲ��� =?";
                    } else {
                        sql1 = "UPDATE emp SET ��ů���� = ��ů����-40 WHERE ���� =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //����
                    ps.executeUpdate();

                    //��ѯ
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "������") {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                    } else {
                        sql = "SELECT id,����,���ű��,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "����", "���ű��", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //����
        JPanel panel1 = new JPanel();
        panel1.add(label);
        panel1.add(deptBox);
        panel1.add(textArea);
        panel1.add(Btn1);
        panel1.add(Btn2);
        panel1.add(Btn3);
        panel1.add(Btn4);
        panel1.add(Btn5);
        panel1.add(Btn6);
//        getContentPane().add(panel1, BorderLayout.SOUTH);
        add(panel1, BorderLayout.SOUTH);


        setVisible(true);

    }

    /**
     * ��������ϢlistתΪ��ά����
     *
     * @param deptList
     * @return
     */
    private Object[][] getFormData(List<Dept> deptList) {
        if (deptList != null && deptList.size() > 0) {
            Object[][] data = new Object[deptList.size()][12];
            for (int i = 0; i < deptList.size(); i++) {
                data[i][0] = deptList.get(i).getId();
                data[i][1] = deptList.get(i).getName();
                data[i][2] = deptList.get(i).getNumber();
                data[i][3] = deptList.get(i).getJob();
                data[i][4] = deptList.get(i).getSalary();
                data[i][5] = deptList.get(i).getAllowance();
                data[i][6] = deptList.get(i).getDay1();
                data[i][7] = deptList.get(i).getDay1sal();
                data[i][8] = deptList.get(i).getDay2();
                data[i][9] = deptList.get(i).getDaysal2();
                data[i][10] = deptList.get(i).getPersonalsal();
                data[i][11] = deptList.get(i).getPersonalsafe();

            }
            return data;
        }
        return null;
    }

    //ˢ��
    public void reFresh() {
        FormPanel formPanel = new FormPanel();
    }
}

//    public static void main(String[] args) {
//        FormPanel formPanel = new FormPanel();
//
//    }
//}
