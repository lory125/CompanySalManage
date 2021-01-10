package csu.tablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class selectPanel extends JPanel {

    JButton selectBtn = new JButton("��ѯ");

    JCheckBox basSal = new JCheckBox("��������");
    JCheckBox deptno = new JCheckBox("���ű��");
    JCheckBox heatMoney = new JCheckBox("��ů����");
    JCheckBox offDays = new JCheckBox("�������", true);
    JCheckBox offDiscount = new JCheckBox("��ٿۿ�", true);
    JCheckBox overDays = new JCheckBox("�Ӱ�����", true);
    JCheckBox overPays = new JCheckBox("�Ӱ๤��", true);
    JCheckBox personalTax = new JCheckBox("��������˰");
    JCheckBox pensionIns = new JCheckBox("����֧�����ϱ���");
    JLabel lable1 = new JLabel("��ѡ���ѯ���ݣ�");
    String sqlOrder[] = new String[]{"id", "����", "���ڲ���", "�������", "��ٿۿ�", "�Ӱ�����", "�Ӱ๤��", "�޸�ʱ��"};
    List<String> sqlR = new ArrayList<>();
    TabPanel table;

//    Box vBox=new Box(12);


    public selectPanel( TabPanel tableI) {
        this.setBackground(new Color(197,228,251));
        table=tableI;
        setLayout(new BorderLayout());
        FlowLayout flow = new FlowLayout();
        JPanel panel = new JPanel();
        panel.setLayout(flow);
        panel.add(lable1);
        panel.add(basSal);
        panel.add(deptno);
        panel.add(heatMoney);
        panel.add(offDays);
        panel.add(offDiscount);
        panel.add(overDays);
        panel.add(overPays);
        panel.add(personalTax);
        panel.add(personalTax);
        panel.add(pensionIns);
        panel.add(selectBtn);
        this.add(panel, BorderLayout.SOUTH);

        //��ʼ����ѯ���
        sqlR.add("id,");
        sqlR.add("����,");
        sqlR.add("���ڲ���");
        sqlR.add(",�������");
        sqlR.add(",��ٿۿ�");
        sqlR.add(",�Ӱ�����");
        sqlR.add(",�Ӱ๤��");

/**
 * ������������¼�
 */
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select Clicked");
                sqlSelect();
            }
        });

        basSal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (basSal.isSelected()) {
                    addSen(",��������");
                } else {
                    delSen(",��������");
                }
            }
        });

        deptno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deptno.isSelected()) {
                    addSen(",���ű��");
                } else {
                    delSen(",���ű��");
                }
            }
        });

        heatMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heatMoney.isSelected()) {
                    addSen(",��ů����");
                } else {
                    delSen(",��ů����");
                }
            }
        });

        offDays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offDays.isSelected()) {
                    addSen(",�������");
                } else {
                    delSen(",�������");
                }
            }
        });

        offDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offDiscount.isSelected()) {
                    addSen(",��ٿۿ�");
                } else {
                    delSen(",��ٿۿ�");
                }
            }
        });

        overDays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overDays.isSelected()) {
                    addSen(",�Ӱ�����");
                } else {
                    delSen(",�Ӱ�����");
                }
            }
        });

        overPays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overPays.isSelected()) {
                    addSen(",�Ӱ๤��");
                } else {
                    delSen(",�Ӱ๤��");
                }
            }
        });

        personalTax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (personalTax.isSelected()) {
                    addSen(",��������˰");
                } else {
                    delSen(",��������˰");
                }
            }
        });

        pensionIns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pensionIns.isSelected()) {
                    addSen(",����֧�����ϱ���");
                } else {
                    delSen(",����֧�����ϱ���");
                }
            }
        });

    }

    public void addSen(String str) {                //����ֶ�
        System.out.println("���***********");
        if (sqlR.contains(str))
            System.out.println("�Ѵ���  " + str);
        else {
            sqlR.add(str);
            System.out.println("���  " + str);
        }

    }

    public void delSen(String str) {                //ɾ���ֶ�
        System.out.println("����************");
        if (sqlR.contains(str)) {
            sqlR.remove(str);
            System.out.println("ɾ��  " + str);
        } else {
            System.out.println("������  " + str);
        }
    }

    public void sqlSelect() {
        String sqlS = "SELECT ";
        for (int i = 0; i < sqlR.size(); i++) {
            sqlS = sqlS + sqlR.get(i);
        }
        sqlS = sqlS + ",�޸�ʱ�� FROM emp ORDER BY ���ڲ���";
        System.out.println(sqlS);

        table.ReFPage(sqlS);
    }
}