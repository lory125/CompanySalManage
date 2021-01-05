package com.csu.salout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.csu.db.DBUtils;
import com.csu.emp.Emp;

public class SalOut extends JPanel{

    public SalOut(){
//   public static void main(String[] args) {
//       JFrame frame = new JFrame("结算工资");
//       frame.setSize(800,400);
//       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       frame.setLocationRelativeTo(null);

//       JPanel panel = new JPanel();
       setLayout(new BorderLayout());
       setPreferredSize(new Dimension(1000,750));

       JLabel deptLabel = new JLabel("部门");
       //下拉框使用
       JComboBox deptBox = new JComboBox();
       deptBox.addItem("财务部");
       deptBox.addItem("管理部");
       deptBox.addItem("研发部");
       deptBox.addItem("人力部");
       deptBox.addItem("安全部");
       deptBox.addItem("业务部");

       //计算工资的按钮
       JButton computeSal = new JButton("计算工资");
       computeSal.setSize(100,40);
       //结算工资按钮
       JButton giveSal = new JButton("发工资");
       giveSal.setSize(100,40);
       //设置按钮响应事件
       computeSal.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String deptName = ((String) deptBox.getSelectedItem());
               String temp = null;
               try {
                   temp = new String(deptName.getBytes(),"UTF-8");
               } catch (UnsupportedEncodingException unsupportedEncodingException) {
                   unsupportedEncodingException.printStackTrace();
               }
               deptName = temp;

               List<Emp> list = new ArrayList<>();
               //连接数据库
               Connection conn = null;//接连对象
               PreparedStatement ps = null;//sql语句的预编译对象
               ResultSet rs = null;

               try {
                   conn = DBUtils.getConnection();

                   //数据更新，计算导入项目工资和保险等
                   String sql0 = "UPDATE emp SET 请假扣款=请假天数*20,加班工资=加班天数*100,个人所得税=基本工资*0.08,个人支付养老保险=基本工资*0.08";
                   ps = conn.prepareStatement(sql0);
                   ps.executeUpdate();

                   String sql = "SELECT id,姓名,所在部门,基本工资,采暖补贴,请假天数,请假扣款,加班天数,加班工资,个人所得税,个人支付养老保险,基本工资+" +
                                "采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资 FROM emp WHERE 所在部门=?";
                   ps = conn.prepareStatement(sql);
                   ps.setString(1,deptName);
                   //执行
                   rs = ps.executeQuery();
                   //解析查询结果集
                   while(rs.next()){
                       Emp dept = new Emp();
                       dept.setId(rs.getInt(1));//取第一个字段的值\
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

                   String[] Names = {"id", "姓名","部门名称", "基本工资", "采暖补贴", "请假天数", "请假扣款", "加班天数", "加班奖金", "个人养老保险", "个人所得税","总工资"};
                   //建表
                   JTable table = new JTable(formdata, Names);
//                   FitTableColumns(table);
                   // 将表格加入到滚动条组件中
                   JScrollPane scrollPane = new JScrollPane(table);
                   add(scrollPane, BorderLayout.CENTER);
//                   frame.setVisible(true);
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               } catch (ClassNotFoundException classNotFoundException) {
                   classNotFoundException.printStackTrace();
               }

           }
       });

       //发工资的事件
       giveSal.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //连接数据库
               Connection conn = null;//接连对象
               PreparedStatement ps = null;//sql语句的预编译对象
               ResultSet rs = null;

               try {
                   conn = DBUtils.getConnection();

                   String sql = "SELECT 姓名,基本工资+采暖补贴-请假天数*20+加班天数*50-基本工资*0.08-基本工资*0.08 总工资,银行卡号 FROM emp WHERE 所在部门=?";
                   ps = conn.prepareStatement(sql);
                   ps.setString(1,(String)deptBox.getSelectedItem());
                   rs = ps.executeQuery();

                   //文件操作
                   File file = new File("sal.txt");
                   //创建文件
                   PrintWriter output = new PrintWriter(file);
                   output.print("姓名" + " ");
                   output.print("总工资" + "   ");
                   output.print("银行卡号");
                   output.println();

                   while(rs.next()){
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

       add(panel2,BorderLayout.SOUTH);

//       setPreferredSize(new Dimension(600,300));


//       frame.add(panel);
//       frame.setVisible(true);

   }

    private static Object[][] getFormData(List<Emp> deptList) {
        if(deptList!=null && deptList.size()>0){
            Object[][] data = new Object[deptList.size()][12];
            for(int i=0;i<deptList.size();i++){
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

    public void FitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }

//    public static void main(String[] args) {
//        JFrame f = new JFrame();
//        f.setSize(800,400);
//        f.add(new SalOut());
//        f.setVisible(true);
//    }




}
