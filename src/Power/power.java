package Power;
import Power.Emp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class power extends JPanel{
    private static int emp=1;//�����ж��Ƿ�Ϊ����Ա

    public static String idStr;


    public void Show()
    {

        if(emp == 0) //������ͨԱ��
        {
            //��ʾ��ͨԱ��ҳ��

            JFrame jFrame2  = new JFrame();
            jFrame2.setSize(320,400);
            jFrame2.setLocationRelativeTo(null);

            JLabel idLabel = new JLabel("����������Ա�����");
            idLabel.setBounds(80,30,200,40);
            JTextField empField = new JTextField();
            empField.setBounds(50,0,200,40);

            //��ӵ��������
            JPanel jPanel2= new JPanel();
            JPanel jPanel3= new JPanel();
            jPanel3.add(idLabel);
            jPanel2.add(empField);
            jPanel2.setLayout(null);
            jPanel3.setLayout(null);

            JButton btnSe = new JButton("��ѯ");
            btnSe.setBounds(100,10,100,40);
            JPanel jpanel4 = new JPanel();
            jpanel4.add(btnSe);
            jpanel4.setLayout(null);
            //��ѯ��ť�¼�
            btnSe.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    /**
                     * �������
                     */

                    String number = empField.getText();//��ȡԱ�����
                    power.idStr = number;
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setSize(800, 600);
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.initContent();
                    mainFrame.setVisible(true);
                    //���ص�¼����
//                    jFrame2.setVisible(false);
                }
            });

            //���չʾ
            Box vBox=  Box.createVerticalBox();
            vBox.add(jPanel3);
            vBox.add(jPanel2);
            vBox.add(jpanel4);

            jFrame2.add(vBox);
            jFrame2.setVisible(true);
            jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else
        {   //����Ա��ҳ��
//            JFrame jFrame1= new JFrame("������Ŀ����");
//            jFrame1.setSize(800,400);//��С
//            jFrame1.setLocationRelativeTo(null);//����
            this.setLayout(new BorderLayout());

            CardLayout c = new CardLayout();
            empPanel mainPanel = new empPanel();//�����
            mainPanel.setLayout(c);

            //���������
            empPanel addCard = new empPanel();
            addCard.initData2();
            empPanel deleteCard = new empPanel();
            deleteCard.initData3();
            empPanel selectCard = new empPanel();
            selectCard.initData1();
            //�������������ӽ������
            mainPanel.add(addCard,"1");
            mainPanel.add(deleteCard,"2");
            mainPanel.add(selectCard,"3");

            //���������ť
            JButton addBtn = new JButton("������Ŀ");
            JButton deleteBtn = new JButton("ɾ����Ŀ");
            JButton selectBtn = new JButton("��ѯ��Ŀ");

            //����Ϸ����
            JPanel northPanel = new JPanel();
            //�Ϸ�������������ť
            northPanel.add(addBtn);
            northPanel.add(deleteBtn);
            northPanel.add(selectBtn);

            //����������������ṹ
            this.add(mainPanel,BorderLayout.CENTER);
            this.add(northPanel,BorderLayout.NORTH);

            //Ϊ������ť����¼�
            //�����¼�
            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"1");
                }
            });
            //ɾ���¼�
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"2");
                }
            });
            //��ѯ��Ŀ
            selectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.show(mainPanel,"3");
                }
            });

            /*******************************/
//            //��Ӱ�ť
//            JButton btnUp = new JButton("����");
//            btnUp.setBounds(0,0,30,20);
//            JButton btnpRdu  = new JButton("ɾ��");
//            btnUp.setBounds(0,0,30,20);
//            JButton btnInqu= new JButton("��ѯ���޸�");
//            btnUp.setBounds(0,0,30,20);
//            //���չʾ
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
//            //����Ա��ѯ���޸ķ�Ӧ�¼�
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
//                    //���ص�¼����
//                    jFrame1.setVisible(false);
//                }
//            });
//
//            //����Ա���ӹ�����Ŀ��ӳ�¼�
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
//                    //���ص�¼����
//                    jFrame1.setVisible(false);
//                }
//            });
//            //����Աɾ��������Ŀ��ӳ�¼�
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
//                    //���ص�¼����
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
