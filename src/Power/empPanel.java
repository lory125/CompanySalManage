package Power;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.List;

public class empPanel extends JPanel {
    JTable table;
    String[] columnNames;//��ͷ
    Object[][] rowData;//�������
    DefaultTableModel model;//�����ʾ������ģ��





    private empDao empDao1 = new empDaoImpl();

    public empPanel()
    {
        setLayout(new BorderLayout());//���ñ߽粼��
    }


    /**
     * Ա����ʼ���������
     */
    public void initData()
    {
        columnNames = new String[]{" id","����","���ű��","���ڲ���","��������","��ů����",
                "�������","��ٿۿ�","�Ӱ�����","�Ӱ๤��","��������˰","����֧�����ϱ���"};

        List<Emp> emplist = empDao1.getDeptByPage(power.idStr);
        rowData = getRowData(emplist);

        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

        //���ñ����������
        table.setFont(new Font(null,Font.BOLD,14));
        table.setRowHeight(30);

        //����ͷ����ڱ߽粼�ֵı���
        add(table.getTableHeader(),BorderLayout.NORTH);
        //����������м�
        add(table,BorderLayout.CENTER);
    }

    //����Ա�Ĳ�ѯ��ʼ�����
    public void initData1()
    {
        columnNames = new String[]{" id","����","���ű��","���ڲ���","��������","��ů����",
                "�������","��ٿۿ�","�Ӱ�����","�Ӱ๤��","��������˰","����֧�����ϱ���"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //����
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

        //����ͷ����ڱ߽粼�ֵı���
        add(table.getTableHeader(),BorderLayout.NORTH);
        //����������м�
        add(table,BorderLayout.CENTER);
        //��ӹ��ܰ���
        JLabel label = new JLabel("��ѯ��ʽ");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("������");
        deptBox.addItem("��������");
        JTextField textArea = new JTextField(10);
        JButton Btn1 = new JButton("��ѯ");//��ѯ��ť

        //��������
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.getTableHeader().setVisible(false);
                    table.setVisible(false);
                    String sql = null;
                    if((String)deptBox.getSelectedItem() == "������"){
                        sql = "SELECT id,����,���ű��,���ڲ���,��������," +
                                "��ů����,�������,��ٿۿ�,�Ӱ�����," +
                                "�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���ڲ��� = ?";
                    }else{
                        sql = "SELECT id,����,���ű��,���ڲ���," +
                                "��������,��ů����,�������,��ٿۿ�,�Ӱ�����," +
                                "�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp WHERE ���� = ?";
                    }

                    List<Emp> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setString(1,textArea.getText());

                    //5��ִ��
                    rs = ps.executeQuery();

                    while (rs.next()){
                        Emp emp = new Emp();
                        emp.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
                        emp.setName(rs.getString(2));
                        emp.setNumber(rs.getInt(3));
                        emp.setJob(rs.getString(4));
                        emp.setSalary(rs.getInt(5));
                        emp.setAllowance(rs.getInt(6));
                        emp.setDay1(rs.getInt(7));
                        emp.setDay1sal(rs.getInt(8));
                        emp.setDay2(rs.getInt(9));
                        emp.setDaysal2(rs.getInt(10));
                        emp.setPersonalsal(rs.getInt(11));
                        emp.setPersonalsafe(rs.getInt(12));
                        list.add(emp);
                    }

                    //����
                    Object[][] rowData= getRowData(list);
                    String[] columnNames = {"id", "����", "���ű��", "��������",
                            "��������", "��ů����", "�������", "��ٿۿ�",
                            "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    //����
                    JTable table1 = new JTable(rowData, columnNames);
                    // �������뵽�����������
                    JScrollPane scrollPane = new JScrollPane(table1);
                    add(scrollPane, BorderLayout.CENTER);


                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(label);
        panel1.add(deptBox);
        panel1.add(textArea);
        panel1.add(Btn1);
        add(panel1,BorderLayout.SOUTH);

        setVisible(true);

    }


    /**
     * ����Ա���ӹ�����Ŀ
     */
    public void initData2()
    {


        columnNames = new String[]{" id","����","���ű��","���ڲ���","��������","��ů����",
                "�������","��ٿۿ�","�Ӱ�����","�Ӱ๤��","��������˰","����֧�����ϱ���"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //����
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);


        //����ͷ����ڱ߽粼�ֵı���
        add(table.getTableHeader(),BorderLayout.NORTH);
        //����������м�
        add(table,BorderLayout.CENTER);
        //��ӹ��ܰ���
        JLabel label = new JLabel("������");
        JTextField textArea = new JTextField(10);
        JLabel slabel = new JLabel("������Ŀ");
        JTextField StextArea = new JTextField(10);
        JTextField MtextArea = new JTextField(10);
        JButton Btn1 = new JButton("����");//��Ӱ�ť

        //�Ѳ���ת��Ϊ����
        Map m1 = new HashMap();
        m1.put("��ˮ��",1);
        m1.put("����",2);
        m1.put("�з���",3);
        m1.put("������",4);
        m1.put("��ȫ��",5);
        m1.put("ҵ��",6);




        //��������
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int deptno = 0;
                    if(m1.containsKey(textArea.getText())){
                        deptno = (int)m1.get(textArea.getText());
                    }

                    table.getTableHeader().setVisible(false);
                    table.setVisible(false);
                    String sql = null;

                    List<Emp> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();

                    //��̬������
                    sql=String.format("ALTER TABLE emp ADD %s int(4) DEFAULT '%s'",StextArea.getText(),MtextArea.getText() );
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();//����

                    conn.close();
                    conn = DBUtils.getConnection();
                    //��ĳ�����ŵ���������ΪĬ��ֵ
                    sql = String.format("UPDATE emp SET %s = 0 WHERE ���ű��!=%d",StextArea.getText(),deptno);
                    ps = conn.prepareStatement(sql);

                    ps.executeUpdate();

                    //�����ܵ�����
                    String countTotal = "SELECT count(id) FROM emp";
                    ps = conn.prepareStatement(countTotal);
                    rs = ps.executeQuery();
                    int totalRowNum = 0;//�ܵ�����
                    while(rs.next()){
                        totalRowNum = rs.getInt(1);
                    }



                    //��ѯ�����к�����ݿ�
                    sql = "SELECT id,����,���ű��,���ڲ���,��������," +
                                "��ů����,�������,��ٿۿ�,�Ӱ�����," +
                                "�Ӱ๤��,��������˰,����֧�����ϱ���,"+StextArea.getText()+" FROM emp WHERE ���ڲ��� = ?";

                    ps = conn.prepareStatement(sql);
                    ps.setString(1,textArea.getText());

                    //5��ִ��
                    rs = ps.executeQuery();


                    //����

                    ResultSetMetaData rsmd = rs.getMetaData();
                    Object[][] rowData = new Object[totalRowNum][rsmd.getColumnCount()];

                    List<String> columnNames = new ArrayList<>();
                    for(int i = 1;i <= rsmd.getColumnCount();i++){
                        if(!columnNames.contains(rsmd.getColumnName(i)))
                            columnNames.add(rsmd.getColumnName(i));
                    }

                    //��ȡ�������
                    for(int i = 0;i <= totalRowNum;i++){
                        if(rs.next()){
                            for(int j = 0;j < rsmd.getColumnCount();j++){
                                rowData[i][j] = rs.getObject(j + 1);
                            }
                        }
                    }


                    String[] tableRow = new String[columnNames.size()];
                    for(int i = 0;i < columnNames.size();i++){
                        tableRow[i] = columnNames.get(i);
                    }


                    //����
                    JTable table1 = new JTable(rowData, tableRow);
                    // �������뵽�����������
                    JScrollPane scrollPane = new JScrollPane(table1);
                    add(scrollPane,BorderLayout.CENTER);

                    setVisible(true);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(label);
        panel1.add(textArea);
        panel1.add(slabel);
        panel1.add(StextArea);
        panel1.add(MtextArea);
        panel1.add(Btn1);
        add(panel1,BorderLayout.SOUTH);

        setVisible(true);

    }


    /**
     *����Աɾ��������Ŀ
     */

    public void initData3()
    {
        columnNames = new String[]{" id","����","���ű��","���ڲ���","��������","��ů����",
                "�������","��ٿۿ�","�Ӱ�����","�Ӱ๤��","��������˰","����֧�����ϱ���"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //����
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

//        //���ñ����������
//        table.setFont(new Font(null,Font.BOLD,14));
//        table.setRowHeight(30);

        //����ͷ����ڱ߽粼�ֵı���
        add(table.getTableHeader(),BorderLayout.NORTH);
        //����������м�
        add(table,BorderLayout.CENTER);
        //��ӹ��ܰ���
        JLabel label = new JLabel("ɾ����Ŀ");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("��ů����");
        deptBox.addItem("�Ӱ๤��");
        JButton Btn1 = new JButton("ɾ��");//��ѯ��ť

        //��������
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.getTableHeader().setVisible(false);
                    table.setVisible(false);
                    String sql = null;
                    String sql1 = null;
                    if((String)deptBox.getSelectedItem() == "��ů����"){
                        sql1 = "UPDATE emp SET ��ů����=0";
                        sql = "SELECT id,����,���ű��,���ڲ���," +
                                "��������,��ů����,�������,��ٿۿ�,�Ӱ�����," +
                                "�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp";
                    }else{
                        sql1 = "UPDATE emp SET �Ӱ๤��=0";
                        sql = "SELECT id,����,���ű��,���ڲ���," +
                                "��������,��ů����,�������,��ٿۿ�,�Ӱ�����," +
                                "�Ӱ๤��,��������˰,����֧�����ϱ��� FROM emp";

                    }

                    List<Emp> list = new ArrayList<>();
                    Connection conn = null;//��������
                    PreparedStatement ps = null;//sql����Ԥ�������
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();

                    ps = conn.prepareStatement(sql1);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(sql);
//                    ps.setString(1,textArea.getText());

                    //5��ִ��
                    rs = ps.executeQuery();

                    while (rs.next()){
                        Emp emp = new Emp();
                        emp.setId(rs.getInt(1));//ȡ��һ���ֶε�ֵ\
                        emp.setName(rs.getString(2));
                        emp.setNumber(rs.getInt(3));
                        emp.setJob(rs.getString(4));
                        emp.setSalary(rs.getInt(5));
                        emp.setAllowance(rs.getInt(6));
                        emp.setDay1(rs.getInt(7));
                        emp.setDay1sal(rs.getInt(8));
                        emp.setDay2(rs.getInt(9));
                        emp.setDaysal2(rs.getInt(10));
                        emp.setPersonalsal(rs.getInt(11));
                        emp.setPersonalsafe(rs.getInt(12));
                        list.add(emp);
                    }

                    //����
                    Object[][] rowData= getRowData(list);
                    String[] columnNames = {"id", "����", "���ű��", "��������",
                            "��������", "��ů����", "�������", "��ٿۿ�",
                            "�Ӱ�����", "�Ӱཱ��", "�������ϱ���", "��������˰"};
                    //����
                    JTable table1 = new JTable(rowData, columnNames);
                    // �������뵽�����������
                    JScrollPane scrollPane = new JScrollPane(table1);
                    add(scrollPane,BorderLayout.CENTER);

                    setVisible(true);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(label);
        panel1.add(deptBox);
//        panel1.add(textArea);
        panel1.add(Btn1);
        add(panel1,BorderLayout.SOUTH);

        setVisible(true);
    }
    /**
     * ��������ϢlistתΪ��ά����
     * @param emplist
     * @return
     */
    private Object[][] getRowData(List<Emp> emplist){
        if(emplist!=null && emplist.size()>0){
            Object[][] data = new Object[emplist.size()][12];
            for(int i=0;i<emplist.size();i++){
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


    public void reFresh(){
        //ˢ��
     empPanel   empPanel1 = new empPanel();
    }



}
