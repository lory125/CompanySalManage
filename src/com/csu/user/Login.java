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
            //���ñ����Խ��ı䴰�ڱ߿���ʽ����
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
        }
        catch(Exception e)
        {
            //TODO exception
        }


        //��¼����
        JFrame jFrame = new JFrame("��¼");
        //���ô�С
        jFrame.setSize(400, 300);
        jFrame.setResizable(false);
        //jFrame.setBackground(new Color(50, 50, 50));
        //������Ե�λ��:������ʾ
        jFrame.setLocationRelativeTo(null);

        //�����û����������
        JLabel nameLabel = new JLabel("�û���");
        //��������Ϳ��
        nameLabel.setBounds(60, 20, 50, 30);
        TextField userField = new TextField(1);
        userField.setBounds(140,20,180,25);



        //userField.setBorder();
        JPanel panel1 = new JPanel();
        //���ı�����������뵽�����
        panel1.add(userField);
        panel1.add(nameLabel);
        panel1.setLayout(null);

        //������������
        JLabel pwdLabel = new JLabel("����");
        //��������Ϳ��
        pwdLabel.setBounds(60, 20, 50, 25);
        JPasswordField pwdField = new JPasswordField(1);
        pwdField.setBounds(140,20,180,25);
        JPanel panel2 = new JPanel();
        //���ı�����������뵽�����
        panel2.add(pwdLabel);
        panel2.add(pwdField);
        panel2.setLayout(null);

        //�����ı����ʽ


        //��ѡ��ť
        JRadioButton isEmp = new JRadioButton("Ա��");
        JRadioButton isGov = new JRadioButton("����Ա",true);
        ButtonGroup group = new ButtonGroup();
        group.add(isEmp);
        group.add(isGov);


        //��ѡ��ť��ǩ
        JLabel loginStatus = new JLabel("��¼���");

        //��ѡ��ť���
        JPanel panel25 = new JPanel();
        panel25.add(loginStatus);
        panel25.add(isEmp);
        panel25.add(isGov);

        //��¼��ť���
        JButton btnLogin = new JButton("��¼");
        btnLogin.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        JButton btnReg = new JButton("ע��");
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel3.add(btnLogin);
        panel3.add(btnReg);

        userField.setText("4127001");
        pwdField.setText("123456");

        //����Ҫ�õĲ���

        //��Ӱ�ť�ĵ����¼�
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //����������ݣ���int��ʾ
                int status = 1;
                //��ȡ��ѡ�������
                if(isEmp.isSelected()){
                    status = 0;
                } else{
                    status = 1;
                }
                //����û���Ԥ�����Ա
                if(status == 1){
                    if(userField.getText().equals("4127001") && new String(pwdField.getPassword()).equals("123456")){
                        JOptionPane.showMessageDialog(null, "��½�ɹ�!");
                        mainFrame mFrame = new mainFrame(status);
                        jFrame.setVisible(false);
                    } else if(!userField.getText().equals("4127001")){
                        JOptionPane.showMessageDialog(null, "����Ա�˺Ŵ���!");
                    } else {
                        JOptionPane.showMessageDialog(null, "����Ա�������!");
                    }
                }  else {//�û������Ա��
                    try {
                        if(userField.getText().length() == 0){
                            JOptionPane.showMessageDialog(null, "�������˺�!!");
                        }

                        ResultSet rst = null;
                        Connection conn = DBUtils.getConnection();
                        String sql = "SELECT ���� FROM emp WHERE �˺�=?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1,userField.getText());
                        rst = ps.executeQuery();

                        String pwd = null;//�����ݿ��ȡ������
                        String pwdF = new String(pwdField.getPassword());//���ı����ȡ������

                        while(rst.next()){
                            pwd = rst.getString(1);
                        }

                        if(pwd.length() == 0){
                            JOptionPane.showMessageDialog(null, "�˺Ų����ڣ�����ע��");
                        }
                        else if(pwdF.length() == 0){
                            JOptionPane.showMessageDialog(null, "����������!");
                        }
                        else if(pwd.equals(pwdF)){
                            JOptionPane.showMessageDialog(null, "��¼�ɹ�!");
                            //�ɹ���ʱ�����֮��Ľ���
                            if(status == 1){
                                mainFrame mFrame = new mainFrame(status);
                            } else {
                                power po = new power();
                                po.setEmp(status);
                                po.Show();
                            }
                            jFrame.setVisible(false);
                            System.out.println("��¼�ɹ�");
                        } else{
                            JOptionPane.showMessageDialog(null, "�������");
                            System.out.println("�������");
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

        //������ֱ���е�����Box
        Box vBox = Box.createVerticalBox();
        vBox.add(panel1);
        vBox.add(panel2);
        vBox.add(panel25);
        vBox.add(panel3);
        //����enter��¼
        jFrame.getRootPane().setDefaultButton(btnLogin);

        //jframe�����������
        jFrame.setContentPane(vBox);

        //��ʾ����
        jFrame.setVisible(true);
    }


}
