package com.csu.user;

import com.csu.db.DBUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    //构造函数
    Register(){

        setSize(400,400);
        setLocationRelativeTo(null);

        JLabel accountLabel = new JLabel("账号");
        JTextField accountFeild = new JTextField();
        accountLabel.setBounds(60,10,50,40);
        accountFeild.setBounds(140,10,180,40);
        JPanel panel1 = new JPanel();
        panel1.add(accountLabel);
        panel1.add(accountFeild);
        panel1.setLayout(null);

        JLabel pwdLabel = new JLabel("密码");
        JTextField pwdFeild = new JTextField();
        pwdLabel.setBounds(60,10,50,40);
        pwdFeild.setBounds(140,10,180,40);
        JPanel panel2 = new JPanel();
        panel2.add(pwdLabel);
        panel2.add(pwdFeild);
        panel2.setLayout(null);

        JLabel nameLabel = new JLabel("姓名");
        JTextField nameFeild = new JTextField();
        nameLabel.setBounds(60,10,50,40);
        nameFeild.setBounds(140,10,180,40);
        JPanel panel3 = new JPanel();
        panel3.add(nameLabel);
        panel3.add(nameFeild);
        panel3.setLayout(null);

        JLabel deptLabel = new JLabel("部门");
        JTextField deptFeild = new JTextField();
        deptLabel.setBounds(60,10,50,40);
        deptFeild.setBounds(140,10,180,40);
        JPanel panel4 = new JPanel();
        panel4.add(deptLabel);
        panel4.add(deptFeild);
        panel4.setLayout(null);

        JPanel panel5 = new JPanel();
        JButton registerBtn = new JButton("注册");
        registerBtn.setSize(90,40);
        registerBtn.setLocation(140,20);
        panel5.add(registerBtn);
        panel5.setLayout(null);

        //注册之后将数据同步到数据库里
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DBUtils.getConnection();
                    String sql = "INSERT INTO emp (账号,密码,姓名,所在部门,新用户) VALUES (?,?,?,?,1)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,accountFeild.getText());
                    ps.setString(2,pwdFeild.getText());
                    ps.setString(3,nameFeild.getText());
                    ps.setString(4,deptFeild.getText());
                    ps.executeUpdate();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            }
        });


        //把面板添加到垂直box
        Box vBox = Box.createVerticalBox();
        vBox.add(panel1);
        vBox.add(panel2);
        vBox.add(panel3);
        vBox.add(panel4);
        vBox.add(panel5);
        //frame设置内容面板
        setContentPane(vBox);
        //设置关闭窗口关闭程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //显示窗口
        setVisible(true);

    }

}
