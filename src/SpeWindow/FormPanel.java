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
        String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
        List<Dept> deptList = formGet.getDeptByPage();
        Object[][] formdata = getFormData(deptList);
        DefaultTableModel model = new DefaultTableModel(formdata,Names);
        table = new JTable();
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        /*****/

//        //定义表中数据
//        List<Dept> deptList = formGet.getDeptByPage();
//        Object[][] formdata = getFormData(deptList);
//
//        //定义表头
//        String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
//        //建表
//        table = new JTable(formdata, Names);
//
//        //设置选中行颜色
//        table.setSelectionBackground(Color.yellow);
//        table.setSelectionForeground(Color.RED);
//        // 将表格加入到滚动条组件中
//        JScrollPane scrollPane = new JScrollPane(table);
////        getContentPane().add(scrollPane, BorderLayout.CENTER);
//        add(scrollPane, BorderLayout.CENTER);
        //添加功能按建
        JLabel label = new JLabel("查询方式(工资区间为数值上下1000)");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("部门名");
        deptBox.addItem("个人姓名");
        deptBox.addItem("工资中心数");
        JTextField textArea = new JTextField(10);
        JButton Btn1 = new JButton("查询");
        JButton Btn2 = new JButton("删除");
        JButton Btn3 = new JButton("工资上调");
        JButton Btn4 = new JButton("工资下调");
        JButton Btn5 = new JButton("增加补贴");
        JButton Btn6 = new JButton("减少补贴");

        //监听触发
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                List<Dept> list = new ArrayList<>();
                Connection conn = null;//接连对象
                PreparedStatement ps = null;//sql语句的预编译对象
                ResultSet rs = null;


                try {
                    conn = DBUtils.getConnection();

                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textArea.getText());
                    } else if ((String) deptBox.getSelectedItem() == "个人姓名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textArea.getText());
                    } else {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 " +
                                "FROM emp WHERE 基本工资 between  ?-1000 and ?+1000";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(textArea.getText()));
                        ps.setInt(2, Integer.parseInt(textArea.getText()));
                    }


                    //5、执行
                    rs = ps.executeQuery();

                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

//                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
//                    //建表
//                    DefaultTableModel model = new DefaultTableModel(formdata,Names);
//                    table = new JTable(formdata, Names);
//                    // 将表格加入到滚动条组件中
//                    JScrollPane scrollPane = new JScrollPane(table);
////                    getContentPane().add(scrollPane, BorderLayout.CENTER);
//                    add(scrollPane, BorderLayout.CENTER);
//                    setVisible(true);
                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //删除
        Btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "DELETE FROM emp WHERE 所在部门 = ?";
                    } else {
                        sql = "DELETE FROM emp WHERE 姓名 = ?";
                    }

                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());

                    //5、执行
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                reFresh();
            }
        });

        //上调工资
        Btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql1 = "UPDATE emp SET 基本工资 = 基本工资*1.1 WHERE 所在部门 =?";
                    } else {
                        sql1 = "UPDATE emp SET 基本工资 = 基本工资*1.1 WHERE 姓名 =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //更新
                    ps.executeUpdate();

                    //查询
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                    } else {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //下调工资
        Btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql1 = "UPDATE emp SET 基本工资 = 基本工资*0.9 WHERE 所在部门 =?";
                    } else {
                        sql1 = "UPDATE emp SET 基本工资 = 基本工资*0.9 WHERE 姓名 =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //更新
                    ps.executeUpdate();

                    //查询
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                    } else {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //补贴对个人和部门
        Btn5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String sql1 = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql1 = "UPDATE emp SET 采暖补贴 = 采暖补贴+100 WHERE 所在部门 =?";
                    } else {
                        sql1 = "UPDATE emp SET 采暖补贴 = 采暖补贴+80 WHERE 姓名 =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //更新
                    ps.executeUpdate();

                    //查询
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                    } else {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
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
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql1 = "UPDATE emp SET 采暖补贴 = 采暖补贴-80 WHERE 所在部门 =?";
                    } else {
                        sql1 = "UPDATE emp SET 采暖补贴 = 采暖补贴-40 WHERE 姓名 =?";
                    }

                    List<Dept> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;


                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, textArea.getText());
                    //更新
                    ps.executeUpdate();

                    //查询
                    String sql = null;
                    if ((String) deptBox.getSelectedItem() == "部门名") {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                    } else {
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, textArea.getText());
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

                    Object[][] formdata = getFormData(list);

                    String[] Names = {"id", "姓名", "部门编号", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    DefaultTableModel  model = new DefaultTableModel(formdata,Names);
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //布局
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
     * 将部门信息list转为二维数组
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

    //刷新
    public void reFresh() {
        FormPanel formPanel = new FormPanel();
    }
}

//    public static void main(String[] args) {
//        FormPanel formPanel = new FormPanel();
//
//    }
//}
