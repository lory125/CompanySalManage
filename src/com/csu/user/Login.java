package com.csu.user;

import com.csu.db.DBUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.sql.*;
import java.text.AttributedCharacterIterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Power.power;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;


public class Login {


    public static void main(String[] args) {

        try
        {
            //设置本属性将改变窗口边框样式定义
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
        }
        catch(Exception e)
        {
            //TODO exception
        }


        //登录窗口
        JFrame jFrame = new JFrame("登录");
        //设置大小
        jFrame.setSize(400, 300);
        jFrame.setResizable(false);
        //jFrame.setBackground(new Color(50, 50, 50));
        //设置相对的位置:居中显示
        jFrame.setLocationRelativeTo(null);

        //创建用户名输入面板
        JLabel nameLabel = new JLabel("用户名");
        //设置坐标和宽高
        nameLabel.setBounds(60, 20, 50, 30);
        TextField userField = new TextField(1);
        userField.setBounds(140,20,180,25);



        //userField.setBorder();
        JPanel panel1 = new JPanel();
        //将文本框和输入框加入到面板里
        panel1.add(userField);
        panel1.add(nameLabel);
        panel1.setLayout(null);

        //密码输入框面板
        JLabel pwdLabel = new JLabel("密码");
        //设置坐标和宽高
        pwdLabel.setBounds(60, 20, 50, 25);
        JPasswordField pwdField = new JPasswordField(1);
        pwdField.setBounds(140,20,180,25);
        JPanel panel2 = new JPanel();
        //将文本框和输入框加入到面板里
        panel2.add(pwdLabel);
        panel2.add(pwdField);
        panel2.setLayout(null);

        //设置文本框格式


        //单选按钮
        JRadioButton isEmp = new JRadioButton("员工");
        JRadioButton isGov = new JRadioButton("管理员",true);
        ButtonGroup group = new ButtonGroup();
        group.add(isEmp);
        group.add(isGov);


        //单选按钮标签
        JLabel loginStatus = new JLabel("登录身份");

        //单选按钮面板
        JPanel panel25 = new JPanel();
        panel25.add(loginStatus);
        panel25.add(isEmp);
        panel25.add(isGov);

        //登录按钮面板
        JButton btnLogin = new JButton("登录");
        btnLogin.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        JButton btnReg = new JButton("注册");
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel3.add(btnLogin);
        panel3.add(btnReg);

        userField.setText("4127001");
        pwdField.setText("123456");

        //设置要用的参数

        //添加按钮的单击事件
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //设置两种身份，用int表示
                int status = 1;
                //获取单选框的内容
                if(isEmp.isSelected()){
                    status = 0;
                } else{
                    status = 1;
                }
                //如果用户是预设管理员
                if(status == 1){
                    if(userField.getText().equals("4127001") && new String(pwdField.getPassword()).equals("123456")){
                        JOptionPane.showMessageDialog(null, "登陆成功!");
                        mainFrame mFrame = new mainFrame(status);
                        jFrame.setVisible(false);
                    } else if(!userField.getText().equals("4127001")){
                        JOptionPane.showMessageDialog(null, "管理员账号错误!");
                    } else {
                        JOptionPane.showMessageDialog(null, "管理员密码错误!");
                    }
                }  else {//用户身份是员工
                    try {
                        if(userField.getText().length() == 0){
                            JOptionPane.showMessageDialog(null, "请输入账号!!");
                        }

                        ResultSet rst = null;
                        Connection conn = DBUtils.getConnection();
                        String sql = "SELECT 密码 FROM emp WHERE 账号=?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1,userField.getText());
                        rst = ps.executeQuery();

                        String pwd = null;//从数据库获取的密码
                        String pwdF = new String(pwdField.getPassword());//从文本框获取的密码

                        while(rst.next()){
                            pwd = rst.getString(1);
                        }

                        if(pwd.length() == 0){
                            JOptionPane.showMessageDialog(null, "账号不存在，请先注册");
                        }
                        else if(pwdF.length() == 0){
                            JOptionPane.showMessageDialog(null, "请输入密码!");
                        }
                        else if(pwd.equals(pwdF)){
                            JOptionPane.showMessageDialog(null, "登录成功!");
                            //成功的时候进入之后的界面
                            if(status == 1){
                                mainFrame mFrame = new mainFrame(status);
                            } else {
                                power po = new power();
                                po.setEmp(status);
                                po.Show();
                            }
                            jFrame.setVisible(false);
                            System.out.println("登录成功");
                        } else{
                            JOptionPane.showMessageDialog(null, "密码错误");
                            System.out.println("密码错误");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }

            }
        });

        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
            }
        });

        //创建垂直排列的容器Box
        Box vBox = Box.createVerticalBox();
        vBox.add(panel1);
        vBox.add(panel2);
        vBox.add(panel25);
        vBox.add(panel3);
        //设置enter登录
        jFrame.getRootPane().setDefaultButton(btnLogin);

        //jframe设置内容面板
        jFrame.setContentPane(vBox);

        //显示窗口
        jFrame.setVisible(true);
    }


}
