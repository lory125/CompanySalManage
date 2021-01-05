package Power;
import Power.Emp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class power extends JPanel{
    private static int emp=1;//用来判断是否为管理员

    public static String idStr;


    public void Show()
    {

        if(emp == 0) //他是普通员工
        {
            //显示普通员工页面

            JFrame jFrame2  = new JFrame();
            jFrame2.setSize(320,400);
            jFrame2.setLocationRelativeTo(null);

            JLabel idLabel = new JLabel("请输入您的员工编号");
            idLabel.setBounds(80,30,200,40);
            JTextField empField = new JTextField();
            empField.setBounds(50,0,200,40);

            //添加到面板中来
            JPanel jPanel2= new JPanel();
            JPanel jPanel3= new JPanel();
            jPanel3.add(idLabel);
            jPanel2.add(empField);
            jPanel2.setLayout(null);
            jPanel3.setLayout(null);

            JButton btnSe = new JButton("查询");
            btnSe.setBounds(100,10,100,40);
            JPanel jpanel4 = new JPanel();
            jpanel4.add(btnSe);
            jpanel4.setLayout(null);
            //查询按钮事件
            btnSe.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    /**
                     * 滚动组件
                     */

                    String number = empField.getText();//获取员工编号
                    power.idStr = number;
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setSize(800, 600);
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.initContent();
                    mainFrame.setVisible(true);
                    //隐藏登录窗口
//                    jFrame2.setVisible(false);
                }
            });

            //面板展示
            Box vBox=  Box.createVerticalBox();
            vBox.add(jPanel3);
            vBox.add(jPanel2);
            vBox.add(jpanel4);

            jFrame2.add(vBox);
            jFrame2.setVisible(true);
            jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else
        {   //管理员的页面
//            JFrame jFrame1= new JFrame("工资项目管理");
//            jFrame1.setSize(800,400);//大小
//            jFrame1.setLocationRelativeTo(null);//居中
            this.setLayout(new BorderLayout());

            CardLayout c = new CardLayout();
            empPanel mainPanel = new empPanel();//主面板
            mainPanel.setLayout(c);

            //三个副面板
            empPanel addCard = new empPanel();
            addCard.initData2();
            empPanel deleteCard = new empPanel();
            deleteCard.initData3();
            empPanel selectCard = new empPanel();
            selectCard.initData1();
            //把三个副面板添加进主面板
            mainPanel.add(addCard,"1");
            mainPanel.add(deleteCard,"2");
            mainPanel.add(selectCard,"3");

            //添加三个按钮
            JButton addBtn = new JButton("增加项目");
            JButton deleteBtn = new JButton("删除项目");
            JButton selectBtn = new JButton("查询项目");

            //添加上方面板
            JPanel northPanel = new JPanel();
            //上方面板添加三个按钮
            northPanel.add(addBtn);
            northPanel.add(deleteBtn);
            northPanel.add(selectBtn);

            //将主，上面板添加入结构
            this.add(mainPanel,BorderLayout.CENTER);
            this.add(northPanel,BorderLayout.NORTH);

            //为三个按钮添加事件
            //增加事件
            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"1");
                }
            });
            //删除事件
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"2");
                }
            });
            //查询项目
            selectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"3");
                }
            });

            /*******************************/
//            //添加按钮
//            JButton btnUp = new JButton("增加");
//            btnUp.setBounds(0,0,30,20);
//            JButton btnpRdu  = new JButton("删除");
//            btnUp.setBounds(0,0,30,20);
//            JButton btnInqu= new JButton("查询及修改");
//            btnUp.setBounds(0,0,30,20);
//            //面板展示
//            JPanel ejPanel= new JPanel();
//            ejPanel.add(btnUp);
//            JPanel fjpanel = new JPanel();
//            fjpanel.add(btnpRdu);
//            JPanel gjpanel = new JPanel();
//            gjpanel.add(btnInqu);
//            Box vBox=  Box.createVerticalBox();
//            vBox.add(ejPanel);
//            vBox.add(fjpanel);
//            vBox.add(gjpanel);
//
//            jFrame1.add(vBox);
//
//
//
//            //管理员查询和修改反应事件
//            btnInqu.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    MainFrame mainFrame = new MainFrame();
//                    mainFrame.setSize(800, 600);
//                    mainFrame.setLocationRelativeTo(null);
//                    mainFrame.initContent1();
//                    mainFrame.setVisible(true);
//                    //隐藏登录窗口
//                    jFrame1.setVisible(false);
//                }
//            });
//
//            //管理员增加工资项目反映事件
//            btnUp.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    MainFrame mainFrame = new MainFrame();
//                    mainFrame.setSize(800, 600);
//                    mainFrame.setLocationRelativeTo(null);
//                    mainFrame.initContent2();
//                    mainFrame.setVisible(true);
//                    //隐藏登录窗口
//                    jFrame1.setVisible(false);
//                }
//            });
//            //管理员删除工资项目反映事件
//            btnpRdu.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    MainFrame mainFrame = new MainFrame();
//                    mainFrame.setSize(800, 600);
//                    mainFrame.setLocationRelativeTo(null);
//                    mainFrame.initContent3();
//                    mainFrame.setVisible(true);
//                    //隐藏登录窗口
//                    jFrame1.setVisible(false);
//                }
//            });


            //          jFrame1.setContentPane(vBox);
//            jFrame1.setVisible(true);

        }

    }

    public void  setEmp(int a){
        this.emp = a;
    }
//    public static void main(String[] args)
//    {
//          show();
//    }

}
