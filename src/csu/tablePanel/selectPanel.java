package csu.tablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class selectPanel extends JPanel {

    JButton selectBtn = new JButton("查询");

    JCheckBox basSal = new JCheckBox("基本工资");
    JCheckBox deptno = new JCheckBox("部门编号");
    JCheckBox heatMoney = new JCheckBox("采暖补贴");
    JCheckBox offDays = new JCheckBox("请假天数", true);
    JCheckBox offDiscount = new JCheckBox("请假扣款", true);
    JCheckBox overDays = new JCheckBox("加班天数", true);
    JCheckBox overPays = new JCheckBox("加班工资", true);
    JCheckBox personalTax = new JCheckBox("个人所得税");
    JCheckBox pensionIns = new JCheckBox("个人支付养老保险");
    JLabel lable1 = new JLabel("请选择查询内容：");
    String sqlOrder[] = new String[]{"id", "姓名", "所在部门", "请假天数", "请假扣款", "加班天数", "加班工资", "修改时间"};
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

        //初始化查询语句
        sqlR.add("id,");
        sqlR.add("姓名,");
        sqlR.add("所在部门");
        sqlR.add(",请假天数");
        sqlR.add(",请假扣款");
        sqlR.add(",加班天数");
        sqlR.add(",加班工资");

/**
 * 向各种组件添加事件
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
                    addSen(",基本工资");
                } else {
                    delSen(",基本工资");
                }
            }
        });

        deptno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deptno.isSelected()) {
                    addSen(",部门编号");
                } else {
                    delSen(",部门编号");
                }
            }
        });

        heatMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heatMoney.isSelected()) {
                    addSen(",采暖补贴");
                } else {
                    delSen(",采暖补贴");
                }
            }
        });

        offDays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offDays.isSelected()) {
                    addSen(",请假天数");
                } else {
                    delSen(",请假天数");
                }
            }
        });

        offDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offDiscount.isSelected()) {
                    addSen(",请假扣款");
                } else {
                    delSen(",请假扣款");
                }
            }
        });

        overDays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overDays.isSelected()) {
                    addSen(",加班天数");
                } else {
                    delSen(",加班天数");
                }
            }
        });

        overPays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overPays.isSelected()) {
                    addSen(",加班工资");
                } else {
                    delSen(",加班工资");
                }
            }
        });

        personalTax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (personalTax.isSelected()) {
                    addSen(",个人所得税");
                } else {
                    delSen(",个人所得税");
                }
            }
        });

        pensionIns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pensionIns.isSelected()) {
                    addSen(",个人支付养老保险");
                } else {
                    delSen(",个人支付养老保险");
                }
            }
        });

    }

    public void addSen(String str) {                //添加字段
        System.out.println("添加***********");
        if (sqlR.contains(str))
            System.out.println("已存在  " + str);
        else {
            sqlR.add(str);
            System.out.println("添加  " + str);
        }

    }

    public void delSen(String str) {                //删除字段
        System.out.println("减少************");
        if (sqlR.contains(str)) {
            sqlR.remove(str);
            System.out.println("删除  " + str);
        } else {
            System.out.println("不存在  " + str);
        }
    }

    public void sqlSelect() {
        String sqlS = "SELECT ";
        for (int i = 0; i < sqlR.size(); i++) {
            sqlS = sqlS + sqlR.get(i);
        }
        sqlS = sqlS + ",修改时间 FROM emp ORDER BY 所在部门";
        System.out.println(sqlS);

        table.ReFPage(sqlS);
    }
}