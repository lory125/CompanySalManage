package com.csu.user;

import Power.power;
import SpeWindow.FormPanel;
import com.csu.db.DBUtils;
import com.csu.emp.Emp;
import csu.MainWindow.SalControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class mainFrame extends JFrame {
    CardLayout card = new CardLayout();
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints layCon = new GridBagConstraints();

    JPanel right = new JPanel();
    JPanel left = new JPanel();

    //工资结算面板
    JPanel p1 = null;
    JPanel p1center = new JPanel();
    //工资项目管理面板
    power po = null;

    FormPanel formPanel = new FormPanel(); //固定工资管理

    SalControl salControl = new SalControl();
    JButton welCom = new JButton("Welcome");
    JButton salProManage = new JButton("工资项目管理");
    JButton fixedSalManage = null;
    JButton inSalProManage = new JButton("导入工资项目管理");
    JScrollPane scrollPane;
    JTable table;

    //构造函数
    mainFrame(int status) {
        setTitle("主界面");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        formPanel.setVisible(false);
        fixedSalManage = new JButton("固定工资管理");

        p1 = new JPanel();
        initP1();


        po = new power();
        po.Show();
        //初始化布局
        initLayout();
        //添加按钮事件

        /************/

        welCom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p1");
            }
        });

        salProManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p2");
            }
        });

        //固定工资管理
        fixedSalManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p3");

            }
        });

        //导入工资项目管理
        inSalProManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p4");
            }
        });

//        JPanel panel = new JPanel();
//        //添加BorderLayout布局
//        panel.setLayout(new BorderLayout());
//        //创建垂直结构
//        Box vbox = Box.createVerticalBox();
//        //把按钮添加到垂直结构
//        vbox.add(salProManage);
//        vbox.add(fixedSalManage);
//        vbox.add(inSalProManage);
//        //把垂直结构添加到布局的西边
//        panel.add(vbox, BorderLayout.WEST);
//
//        //显示图片
//        JLabel imgLabel = new JLabel(new ImageIcon("Img/欢迎来到工资管理系统.png"));
//        panel.add(imgLabel, BorderLayout.CENTER);
//
//        //把面板添加到主结构
//        add(panel);
        //显示界面
        left.setBackground(Color.GRAY);

        this.setLayout(layout);
        this.add(welCom);
        this.add(salProManage);
        this.add(fixedSalManage);
        this.add(inSalProManage);
        this.add(left);
        this.add(right);
        setVisible(true);
    }

    //初始化结算页面
    void initP1() {
        p1.setLayout(new BorderLayout());
        String[] columnNames = {"id", "姓名", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税", "总工资"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable();
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        p1.add(scrollPane, BorderLayout.CENTER);

        JLabel deptLabel = new JLabel("部门");
        //下拉框使用
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("财务部");
        deptBox.addItem("管理部");
        deptBox.addItem("研发部");
        deptBox.addItem("人力部");
        deptBox.addItem("安全部");
        deptBox.addItem("业务部");

        //计算工资的按钮
        JButton computeSal = new JButton("计算工资");
        computeSal.setSize(100, 40);
        //结算工资按钮
        JButton giveSal = new JButton("发工资");
        giveSal.setSize(100, 40);
        //设置按钮响应事件
        computeSal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String deptName = (String) deptBox.getSelectedItem();
                String temp = null;
                try {
                    temp = new String(deptName.getBytes(), "UTF-8");
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                deptName = temp;
                List<Emp> list = new ArrayList<>();
                //连接数据库
                Connection conn = null;//接连对象
                PreparedStatement ps = null;//sql语句的预编译对象
                ResultSet rs = null;

                try {
                    conn = DBUtils.getConnection();

                    //数据更新，计算导入项目工资和保险等
                    String sql0 = "UPDATE emp SET 请假扣款=请假天数*20,加班工资=加班天数*100,个人所得税=基本工资*0.08,个人支付养老保险=基本工资*0.08";
                    ps = conn.prepareStatement(sql0);
                    ps.executeUpdate();

//                    String sql = "SELECT id,姓名,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险,基本工资+" +
//                            "采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资 FROM emp WHERE 所在部门=?";
                    String sql = String.format("SELECT id,姓名,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险,基本工资+" +
                            "采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资 FROM emp WHERE 所在部门='%s'", deptName);
                    ps = conn.prepareStatement(sql);
//                    ps.setString(1,deptName);
                    //执行
                    rs = ps.executeQuery();
                    //解析查询结果集
                    while (rs.next()) {
                        Emp dept = new Emp();
                        dept.setId(rs.getInt(1));//取第一个字段的值\
                        dept.setName(rs.getString(2));
                        dept.setJob(rs.getString(3));
                        dept.setSalary(rs.getInt(4));
                        dept.setAllowance(rs.getInt(5));
                        dept.setDay1(rs.getInt(6));
                        dept.setDay1sal(rs.getInt(7));
                        dept.setDay2(rs.getInt(8));
                        dept.setDaysal2(rs.getInt(9));
                        dept.setPersonalsal(rs.getInt(10));
                        dept.setPersonalsafe(rs.getInt(11));
                        dept.setTotalSal(rs.getInt(12));
                        list.add(dept);
                    }
                    Object[][] formdata = getFormData(list);
//                    sql = String.format("UPDATE emp SET %s = %s WHERE id = %s", col, data, id);


                    String[] Names = {"id", "姓名", "部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税", "总工资"};
                    DefaultTableModel model = new DefaultTableModel(formdata, Names);
                    table.setModel(model);
                    //将表格添加到结算页面的中心
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        //发工资的事件
        giveSal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //连接数据库
                Connection conn = null;//接连对象
                PreparedStatement ps = null;//sql语句的预编译对象
                ResultSet rs = null;

                try {
                    conn = DBUtils.getConnection();

                    String deptName = (String) deptBox.getSelectedItem();
                    //String sql = String.format("SELECT 姓名,基本工资+采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资,银行卡号 FROM emp WHERE 所在部门='%s'", deptName);
                    String sql = String.format("SELECT 姓名,基本工资+采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资,银行卡号 FROM emp WHERE 所在部门='%s'", deptName);
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    //文件操作
                    File file = new File("sal.txt");
                    //创建文件
                    PrintWriter output = new PrintWriter(file);
                    output.print("姓名" + " ");
                    output.print("总工资" + "   ");
                    output.print("银行卡号");
                    output.println();
                    //é“?è???????·
                    while (rs.next()) {
                        output.print(rs.getString(1) + " ");
                        output.print(rs.getInt(2) + " ");
                        output.print(rs.getString(3));
                        output.println();
                    }
                    output.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException | FileNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }


            }
        });

        JPanel panel2 = new JPanel();

        panel2.add(deptLabel);
        panel2.add(deptBox);
        panel2.add(computeSal);
        panel2.add(giveSal);
        //把p1下面的按钮加进去

        p1.add(panel2, BorderLayout.SOUTH);
//        p1.setVisible(true);
    }

    //转换list为Object[][]的函数
    private static Object[][] getFormData(List<Emp> deptList) {
        if (deptList != null && deptList.size() > 0) {
            Object[][] data = new Object[deptList.size()][12];
            for (int i = 0; i < deptList.size(); i++) {
                data[i][0] = deptList.get(i).getId();
                data[i][1] = deptList.get(i).getName();
                data[i][2] = deptList.get(i).getJob();
                data[i][3] = deptList.get(i).getSalary();
                data[i][4] = deptList.get(i).getAllowance();
                data[i][5] = deptList.get(i).getDay1();
                data[i][6] = deptList.get(i).getDay1sal();
                data[i][7] = deptList.get(i).getDay2();
                data[i][8] = deptList.get(i).getDaysal2();
                data[i][9] = deptList.get(i).getPersonalsal();
                data[i][10] = deptList.get(i).getPersonalsafe();
                data[i][11] = deptList.get(i).getTotalSal();

            }
            return data;
        }
        return null;
    }

    //
    private Object[][] getRowData(List<Power.Emp> emplist) {
        if (emplist != null && emplist.size() > 0) {
            Object[][] data = new Object[emplist.size()][12];
            for (int i = 0; i < emplist.size(); i++) {
                data[i][0] = emplist.get(i).getId();
                data[i][1] = emplist.get(i).getName();
                data[i][2] = emplist.get(i).getNumber();
                data[i][3] = emplist.get(i).getJob();
                data[i][4] = emplist.get(i).getSalary();
                data[i][5] = emplist.get(i).getAllowance();
                data[i][6] = emplist.get(i).getDay1();
                data[i][7] = emplist.get(i).getDay1sal();
                data[i][8] = emplist.get(i).getDay2();
                data[i][9] = emplist.get(i).getDaysal2();
                data[i][10] = emplist.get(i).getPersonalsal();
                data[i][11] = emplist.get(i).getPersonalsafe();


            }
            return data;
        }
        return null;
    }


    public void initLayout() {
        /**
         * 右侧
         */
        right.setLayout(card);
        right.setMaximumSize(new Dimension(300, 300));

        right.add(p1, "p1"); //工资结算


        p1.setBackground(Color.gray);
        right.add(po, "p2");//工资项目管理
//        right.add("p3",p3);
        right.add(formPanel, "p3");
        right.add("p4", salControl);
        /**
         * 左侧
         */
        layCon.fill = GridBagConstraints.BOTH;
        layCon.gridx = 0;
        layCon.gridy = 0;
        layCon.gridwidth = 1;
        layCon.gridheight = 1;
//        layCon.ipadx=150;
//        layCon.ipady= 800;
        layCon.weightx = 0.1;
        layCon.weighty = 0.1;
        layout.setConstraints(welCom, layCon);

        layCon.gridx = 0;
        layCon.gridy = 1;
        layCon.gridwidth = 1;
        layCon.gridheight = 1;

//        layCon.ipadx=150;
//        layCon.ipady= 800;
        layCon.weightx = 0.1;
        layCon.weighty = 0.1;
        layout.setConstraints(salProManage, layCon);

        layCon.gridx = 0;
        layCon.gridy = 2;
        layCon.gridwidth = 1;
        layCon.gridheight = 1;
//        layCon.ipadx=150;
//        layCon.ipady= 800;
        layCon.weightx = 0.1;
        layCon.weighty = 0.1;
        layout.setConstraints(fixedSalManage, layCon);

        layCon.gridx = 0;
        layCon.gridy = 3;
        layCon.gridwidth = 1;
        layCon.gridheight = 1;
//        layCon.ipadx=150;
//        layCon.ipady= 800;
        layCon.weightx = 0.1;
        layCon.weighty = 0.1;
        layout.setConstraints(inSalProManage, layCon);

        layCon.gridx = 0;
        layCon.gridy = 4;
        layCon.gridwidth = 1;
        layCon.gridheight = 6;
//        layCon.ipadx=150;
//        layCon.ipady= 800;
        layCon.weightx = 0.1;
        layCon.weighty = 0.6;
        layout.setConstraints(left, layCon);

        //layCon.anchor=GridBagConstraints.CENTER;
        layCon.gridx = 1;
        layCon.gridy = 0;
        layCon.gridheight = 10;
        layCon.gridwidth = 9;
//        layCon.ipadx=1050;
//        layCon.ipady= 800;
        layCon.weightx = 0.9;
        layCon.weighty = 1;
        layout.setConstraints(right, layCon);
    }
}
