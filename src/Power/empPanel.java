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
    String[] columnNames;//表头
    Object[][] rowData;//表格数据
    DefaultTableModel model;//表格显示的数据模型





    private empDao empDao1 = new empDaoImpl();

    public empPanel()
    {
        setLayout(new BorderLayout());//设置边界布局
    }


    /**
     * 员工初始化表格数据
     */
    public void initData()
    {
        columnNames = new String[]{" id","姓名","部门编号","所在部门","基本工资","采暖补贴",
                "请假天数","请假扣款","加班天数","加班工资","个人所得税","个人支付养老保险"};

        List<Emp> emplist = empDao1.getDeptByPage(power.idStr);
        rowData = getRowData(emplist);

        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

        //设置表格的内容相关
        table.setFont(new Font(null,Font.BOLD,14));
        table.setRowHeight(30);

        //将表头添加在边界布局的北部
        add(table.getTableHeader(),BorderLayout.NORTH);
        //表格内容在中间
        add(table,BorderLayout.CENTER);
    }

    //管理员的查询初始化表格
    public void initData1()
    {
        columnNames = new String[]{" id","姓名","部门编号","所在部门","基本工资","采暖补贴",
                "请假天数","请假扣款","加班天数","加班工资","个人所得税","个人支付养老保险"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //建表
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

        //将表头添加在边界布局的北部
        add(table.getTableHeader(),BorderLayout.NORTH);
        //表格内容在中间
        add(table,BorderLayout.CENTER);
        //添加功能按建
        JLabel label = new JLabel("查询方式");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("部门名");
        deptBox.addItem("个人姓名");
        JTextField textArea = new JTextField(10);
        JButton Btn1 = new JButton("查询");//查询按钮

        //监听触发
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.getTableHeader().setVisible(false);
                    table.setVisible(false);
                    String sql = null;
                    if((String)deptBox.getSelectedItem() == "部门名"){
                        sql = "SELECT id,姓名,部门编号,所在部门,基本工资," +
                                "采暖补贴,请假天数,请假扣款,加班天数," +
                                "加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 所在部门 = ?";
                    }else{
                        sql = "SELECT id,姓名,部门编号,所在部门," +
                                "基本工资,采暖补贴,请假天数,请假扣款,加班天数," +
                                "加班工资,个人所得税,个人支付养老保险 FROM emp WHERE 姓名 = ?";
                    }

                    List<Emp> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setString(1,textArea.getText());

                    //5、执行
                    rs = ps.executeQuery();

                    while (rs.next()){
                        Emp emp = new Emp();
                        emp.setId(rs.getInt(1));//取第一个字段的值\
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

                    //建表
                    Object[][] rowData= getRowData(list);
                    String[] columnNames = {"id", "姓名", "部门编号", "部门名称",
                            "基本工资", "采暖补贴", "请假天数", "请假扣款",
                            "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    //建表
                    JTable table1 = new JTable(rowData, columnNames);
                    // 将表格加入到滚动条组件中
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
     * 管理员增加工资项目
     */
    public void initData2()
    {


        columnNames = new String[]{" id","姓名","部门编号","所在部门","基本工资","采暖补贴",
                "请假天数","请假扣款","加班天数","加班工资","个人所得税","个人支付养老保险"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //建表
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);


        //将表头添加在边界布局的北部
        add(table.getTableHeader(),BorderLayout.NORTH);
        //表格内容在中间
        add(table,BorderLayout.CENTER);
        //添加功能按建
        JLabel label = new JLabel("部门名");
        JTextField textArea = new JTextField(10);
        JLabel slabel = new JLabel("工资项目");
        JTextField StextArea = new JTextField(10);
        JTextField MtextArea = new JTextField(10);
        JButton Btn1 = new JButton("增加");//添加按钮

        //把部门转换为数字
        Map m1 = new HashMap();
        m1.put("划水部",1);
        m1.put("管理部",2);
        m1.put("研发部",3);
        m1.put("人力部",4);
        m1.put("安全部",5);
        m1.put("业务部",6);




        //监听触发
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
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();

                    //动态增加列
                    sql=String.format("ALTER TABLE emp ADD %s int(4) DEFAULT '%s'",StextArea.getText(),MtextArea.getText() );
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();//问题

                    conn.close();
                    conn = DBUtils.getConnection();
                    //把某个部门的新增列置为默认值
                    sql = String.format("UPDATE emp SET %s = 0 WHERE 部门编号!=%d",StextArea.getText(),deptno);
                    ps = conn.prepareStatement(sql);

                    ps.executeUpdate();

                    //计算总的行数
                    String countTotal = "SELECT count(id) FROM emp";
                    ps = conn.prepareStatement(countTotal);
                    rs = ps.executeQuery();
                    int totalRowNum = 0;//总的行数
                    while(rs.next()){
                        totalRowNum = rs.getInt(1);
                    }



                    //查询增加列后的数据库
                    sql = "SELECT id,姓名,部门编号,所在部门,基本工资," +
                                "采暖补贴,请假天数,请假扣款,加班天数," +
                                "加班工资,个人所得税,个人支付养老保险,"+StextArea.getText()+" FROM emp WHERE 所在部门 = ?";

                    ps = conn.prepareStatement(sql);
                    ps.setString(1,textArea.getText());

                    //5、执行
                    rs = ps.executeQuery();


                    //建表

                    ResultSetMetaData rsmd = rs.getMetaData();
                    Object[][] rowData = new Object[totalRowNum][rsmd.getColumnCount()];

                    List<String> columnNames = new ArrayList<>();
                    for(int i = 1;i <= rsmd.getColumnCount();i++){
                        if(!columnNames.contains(rsmd.getColumnName(i)))
                            columnNames.add(rsmd.getColumnName(i));
                    }

                    //获取表格数据
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


                    //建表
                    JTable table1 = new JTable(rowData, tableRow);
                    // 将表格加入到滚动条组件中
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
     *管理员删除工资项目
     */

    public void initData3()
    {
        columnNames = new String[]{" id","姓名","部门编号","所在部门","基本工资","采暖补贴",
                "请假天数","请假扣款","加班天数","加班工资","个人所得税","个人支付养老保险"};

        List<Emp> emplist = empDao1.getPage();
        rowData = getRowData(emplist);

        //建表
        model = new DefaultTableModel(rowData,columnNames);
        table = new JTable();
        table.setModel(model);

//        //设置表格的内容相关
//        table.setFont(new Font(null,Font.BOLD,14));
//        table.setRowHeight(30);

        //将表头添加在边界布局的北部
        add(table.getTableHeader(),BorderLayout.NORTH);
        //表格内容在中间
        add(table,BorderLayout.CENTER);
        //添加功能按建
        JLabel label = new JLabel("删除项目");
        JComboBox deptBox = new JComboBox();
        deptBox.addItem("采暖补贴");
        deptBox.addItem("加班工资");
        JButton Btn1 = new JButton("删除");//查询按钮

        //监听触发
        Btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.getTableHeader().setVisible(false);
                    table.setVisible(false);
                    String sql = null;
                    String sql1 = null;
                    if((String)deptBox.getSelectedItem() == "采暖补贴"){
                        sql1 = "UPDATE emp SET 采暖补贴=0";
                        sql = "SELECT id,姓名,部门编号,所在部门," +
                                "基本工资,采暖补贴,请假天数,请假扣款,加班天数," +
                                "加班工资,个人所得税,个人支付养老保险 FROM emp";
                    }else{
                        sql1 = "UPDATE emp SET 加班工资=0";
                        sql = "SELECT id,姓名,部门编号,所在部门," +
                                "基本工资,采暖补贴,请假天数,请假扣款,加班天数," +
                                "加班工资,个人所得税,个人支付养老保险 FROM emp";

                    }

                    List<Emp> list = new ArrayList<>();
                    Connection conn = null;//接连对象
                    PreparedStatement ps = null;//sql语句的预编译对象
                    ResultSet rs = null;

                    conn = DBUtils.getConnection();

                    ps = conn.prepareStatement(sql1);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(sql);
//                    ps.setString(1,textArea.getText());

                    //5、执行
                    rs = ps.executeQuery();

                    while (rs.next()){
                        Emp emp = new Emp();
                        emp.setId(rs.getInt(1));//取第一个字段的值\
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

                    //建表
                    Object[][] rowData= getRowData(list);
                    String[] columnNames = {"id", "姓名", "部门编号", "部门名称",
                            "基本工资", "采暖补贴", "请假天数", "请假扣款",
                            "加班天数", "加班奖金", "个人养老保险", "个人所得税"};
                    //建表
                    JTable table1 = new JTable(rowData, columnNames);
                    // 将表格加入到滚动条组件中
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
     * 将部门信息list转为二维数组
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
        //刷新
     empPanel   empPanel1 = new empPanel();
    }



}
