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
    private String[] columnNames;//��ͷ
    private Object[][] rowData;//��������
    private DefaultTableModel model;//�����ʾ������ģ��
    private int curPage = 1;//��ǰҳ
    private int pageSize = 10;//ÿҳ��ʾ������
    private int totalCount = 0;//�ܹ�����ҳ
    private JButton ReFBtn;
    private String tempSql;

    /**
     * ���캯��
     */
    public TabPanel() {
        ReFBtn = new JButton("ˢ��");
        totalEmpCount.setText("     �ܼ�¼������   " + String.valueOf(salDao.getTotalCount()));
        //���ñ߽粼��
        setLayout(new BorderLayout());
        this.initData();
        //���������Ӽ�����
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
        DefaultTableModel model = (DefaultTableModel) table.getModel();  //��ȡ�������ݵ�ģʽ
        Number id = (Number) model.getValueAt(row, 0);                   //��ȡID
        String a = model.getColumnName(col);                            //��ȡ����
        System.out.println(id + "    " + a + "   " + model.getValueAt(row, col) + row + col);
        salDao.updateSal(id, a, String.valueOf(model.getValueAt(row, col)));//����db���޸Ļ�ɾ������
        ReFPage(tempSql);
    }

    /**
     * ��ʼ���������
     */
    public void initData() {


        columnNames = salDao.getColName(null);
        rowData=salDao.getEmpInfo(null);
        model = new DefaultTableModel(rowData, columnNames);

        table.setModel(model);
        //���ñ����������
        table.setFont(new Font(null, Font.BOLD, 14));
        table.setRowHeight(30);
//        //����ͷ����ڱ߽粼�ֵı��������棩
//        add(table.getTableHeader(),BorderLayout.NORTH);
//        //�������������в�
//        add(table,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
//�ӵ�pane��
        add(scrollPane);
        //setSize(500,200);
        setVisible(true);
        FitTableColumns(table);
    }

    /**
     * ʹ�б�������ݵ����п�
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
            header.setResizingColumn(column); // ���к���Ҫ
            //column.setWidth(width + myTable.getIntercellSpacing().width);
            column.setWidth(900/table.getColumnCount() );
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
            // tcr.setHorizontalAlignment(JLabel.CENTER);
            tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
            myTable.setDefaultRenderer(Object.class, tcr);

        }
    }

    /**
     * ˢ�±��
     * @param sqlOrd
     */
    public void ReFPage(String sqlOrd) {
        totalEmpCount.setText("     �ܼ�¼������   " + salDao.getTotalCount());
        //�����ݿ��ȡ��ǰҳ�ļ�¼
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
        //��ӵ�deptpanel�ĵײ�
        add(toolBar, BorderLayout.SOUTH);
    }
}
