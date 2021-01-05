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

    //���ʽ������
    JPanel p1 = null;
    JPanel p1center = new JPanel();
    //������Ŀ�������
    power po = null;

    FormPanel formPanel = new FormPanel(); //�̶����ʹ���

    SalControl salControl = new SalControl();
    JButton welCom = new JButton("Welcome");
    JButton salProManage = new JButton("������Ŀ����");
    JButton fixedSalManage = null;
    JButton inSalProManage = new JButton("���빤����Ŀ����");
    JScrollPane scrollPane;
    JTable table;

    //���캯��
    mainFrame(int status) {
        setTitle("������");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        formPanel.setVisible(false);
        fixedSalManage = new JButton("�̶����ʹ���");

        p1 = new JPanel();
        initP1();


        po = new power();
        po.Show();
        //��ʼ������
        initLayout();
        //��Ӱ�ť�¼�

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

        //�̶����ʹ���
        fixedSalManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p3");

            }
        });

        //���빤����Ŀ����
        inSalProManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(right, "p4");
            }
        });

//        JPanel panel = new JPanel();
//        //���BorderLayout����
//        panel.setLayout(new BorderLayout());
//        //������ֱ�ṹ
//        Box vbox = Box.createVerticalBox();
//        //�Ѱ�ť��ӵ���ֱ�ṹ
//        vbox.add(salProManage);
//        vbox.add(fixedSalManage);
//        vbox.add(inSalProManage);
//        //�Ѵ�ֱ�ṹ��ӵ����ֵ�����
//        panel.add(vbox, BorderLayout.WEST);
//
//        //��ʾͼƬ
//        JLabel imgLabel = new JLabel(new ImageIcon("Img/��ӭ�������ʹ���ϵͳ.png"));
//        panel.add(imgLabel, BorderLayout.CENTER);
//
//        //�������ӵ����ṹ
//        add(panel);
        //��ʾ����
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

    //��ʼ������ҳ��
    void initP1() {
        p1.setLayout(new BorderLayout());
        String[] columnNames = {"id", "����", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰", "�ܹ���"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable();
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        p1.add(scrollPane, BorderLayout.CENTER);

        JLabel deptLabel = new JLabel("����");
        //������ʹ��
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("����");
        deptBox.addItem("����");
        deptBox.addItem("�з���");
        deptBox.addItem("������");
        deptBox.addItem("��ȫ��");
        deptBox.addItem("ҵ��");

        //���㹤�ʵİ�ť
        JButton computeSal = new JButton("���㹤��");
        computeSal.setSize(100, 40);
        //���㹤�ʰ�ť
        JButton giveSal = new JButton("������");
        giveSal.setSize(100, 40);
        //���ð�ť��Ӧ�¼�
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
                //�������ݿ�
                Connection conn = null;//��������
                PreparedStatement ps = null;//sql����Ԥ�������
                ResultSet rs = null;

                try {
                    conn = DBUtils.getConnection();

                    //���ݸ��£����㵼����Ŀ���ʺͱ��յ�
                    String sql0 = "UPDATE emp SET ��ٿۿ�=�������*20,�Ӱ๤��=�Ӱ�����*100,��������˰=��������*0.08,����֧�����ϱ���=��������*0.08";
                    ps = conn.prepareStatement(sql0);
                    ps.executeUpdate();

//                    String sql = "SELECT id,����,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ���,��������+" +
//                            "��ů����-�������*20+�Ӱ�����*50-��������*0.08-��������*0.08 �ܹ��� FROM emp WHERE ���ڲ���=?";
                    String sql = String.format("SELECT id,����,���ڲ���,��������,��ů����,�������,��ٿۿ�,�Ӱ�����,�Ӱ๤��,��������˰,����֧�����ϱ���,��������+" +
                            "��ů����-�������*20+�Ӱ�����*50-��������*0.08-��������*0.08 �ܹ��� FROM emp WHERE ���ڲ���='%s'", deptName);
                    ps = conn.prepareStatement(sql);
//                    ps.setString(1,deptName);
                    //ִ��
                    rs = ps.executeQuery();
                    //������ѯ�����
                    while (rs.next()) {
                        Emp dept = new Emp();
                        dept.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
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


                    String[] Names = {"id", "����", "��������", "��������", "��ů����", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰", "�ܹ���"};
                    DefaultTableModel model = new DefaultTableModel(formdata, Names);
                    table.setModel(model);
                    //�������ӵ�����ҳ�������
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        //�����ʵ��¼�
        giveSal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //�������ݿ�
                Connection conn = null;//��������
                PreparedStatement ps = null;//sql����Ԥ�������
                ResultSet rs = null;

                try {
                    conn = DBUtils.getConnection();

                    String deptName = (String) deptBox.getSelectedItem();
                    //String sql = String.format("SELECT ����,��������+��ů����-�������*20+�Ӱ�����*50-��������*0.08-��������*0.08 �ܹ���,���п��� FROM emp WHERE ���ڲ���='%s'", deptName);
                    String sql = String.format("SELECT ����,��������+��ů����-�������*20+�Ӱ�����*50-��������*0.08-��������*0.08 �ܹ���,���п��� FROM emp WHERE ���ڲ���='%s'", deptName);
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    //�ļ�����
                    File file = new File("sal.txt");
                    //�����ļ�
                    PrintWriter output = new PrintWriter(file);
                    output.print("����" + " ");
                    output.print("�ܹ���" + "   ");
                    output.print("���п���");
                    output.println();
                    //����?��???????��
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
        //��p1����İ�ť�ӽ�ȥ

        p1.add(panel2, BorderLayout.SOUTH);
//        p1.setVisible(true);
    }

    //ת��listΪObject[][]�ĺ���
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
         * �Ҳ�
         */
        right.setLayout(card);
        right.setMaximumSize(new Dimension(300, 300));

        right.add(p1, "p1"); //���ʽ���


        p1.setBackground(Color.gray);
        right.add(po, "p2");//������Ŀ����
//        right.add("p3",p3);
        right.add(formPanel, "p3");
        right.add("p4", salControl);
        /**
         * ���
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
