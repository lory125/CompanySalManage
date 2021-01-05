package csu.tablePanel;

import csu.dao.salDao;
import csu.dao.salDaoImpl;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public class TabPanel extends JPanel {

    JToolBar toolBar = new JToolBar();
    JLabel totalEmpCount = new JLabel();
    salDao salDao = new salDaoImpl();
    private JTable table = new JTable();
    private String[] columnNames;//表头
    private Object[][] rowData;//表格的数据
    private DefaultTableModel model;//表格显示的数据模型
    private int curPage = 1;//当前页
    private int pageSize = 10;//每页显示多少条
    private int totalCount = 0;//总共多少页
    private JButton ReFBtn;
    private String tempSql;

    /**
     * 构造函数
     */
    public TabPanel() {
        ReFBtn = new JButton("刷新");
        totalEmpCount.setText("     总记录条数：   " + String.valueOf(salDao.getTotalCount()));
        //设置边界布局
        setLayout(new BorderLayout());
        this.initData();
        //给给表格添加监听器
        table.getModel().addTableModelListener(e -> {
            System.out.println("Enter Received");
            updateData(table);
        });
        ReFPage(null);
        refBtn();
    }

    private void updateData(JTable table) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        DefaultTableModel model = (DefaultTableModel) table.getModel();  //获取管理数据的模式
        Number id = (Number) model.getValueAt(row, 0);                   //获取ID
        String a = model.getColumnName(col);                            //获取列名
        System.out.println(id + "    " + a + "   " + model.getValueAt(row, col) + row + col);
        salDao.updateSal(id, a, String.valueOf(model.getValueAt(row, col)));//连接db做修改或删除操作
        ReFPage(tempSql);
    }

    /**
     * 初始化表格数据
     */
    public void initData() {


        columnNames = salDao.getColName(null);
        rowData=salDao.getEmpInfo(null);
        model = new DefaultTableModel(rowData, columnNames);

        table.setModel(model);
        //设置表格的内容相关
        table.setFont(new Font(null, Font.BOLD, 14));
        table.setRowHeight(30);
//        //将表头添加在边界布局的北部（上面）
//        add(table.getTableHeader(),BorderLayout.NORTH);
//        //表格内容添加在中部
//        add(table,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
//加到pane中
        add(scrollPane);
        //setSize(500,200);
        setVisible(true);
        FitTableColumns(table);
    }

    /**
     * 使列表根据内容调整列宽
     *
     * @param myTable
     */
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
            //column.setWidth(width + myTable.getIntercellSpacing().width);
            column.setWidth(900/table.getColumnCount() );
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
            // tcr.setHorizontalAlignment(JLabel.CENTER);
            tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
            myTable.setDefaultRenderer(Object.class, tcr);

        }
    }

    /**
     * 刷新表格
     * @param sqlOrd
     */
    public void ReFPage(String sqlOrd) {
        totalEmpCount.setText("     总记录条数：   " + salDao.getTotalCount());
        //从数据库获取当前页的记录
        if (tempSql==null||!tempSql.equals(sqlOrd))
                tempSql=sqlOrd;
        rowData = salDao.getEmpInfo(sqlOrd);
        columnNames = salDao.getColName(sqlOrd);
        model = new DefaultTableModel(rowData, columnNames);
        table.setModel(model);
        FitTableColumns(table);
        table.getModel().addTableModelListener(e -> {
            System.out.println("Enter Received");
            updateData(table);
        });
    }
    public void refBtn(){
        ReFBtn.addActionListener(e -> {
            ReFPage(tempSql);
        });
        toolBar.add(ReFBtn);
        toolBar.add(totalEmpCount);
        //添加到deptpanel的底部
        add(toolBar, BorderLayout.SOUTH);
    }
}
