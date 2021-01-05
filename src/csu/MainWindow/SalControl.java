package csu.MainWindow;

import csu.tablePanel.TabPanel;
import csu.tablePanel.selectPanel;

import javax.swing.*;
import java.awt.*;

public class SalControl extends JPanel {
    public SalControl() throws HeadlessException {
        //super.setTitle("工资管理");
        this.setSize(1100, 800);
        //this.setResizable(false);
        TabPanel table = new TabPanel();
        selectPanel selPanel = new selectPanel(table);

        /*布局*/

//        GridLayout layout = new GridLayout(2, 1);
//        this.setLayout(layout);
//        this.add(selPanel);
//        this.add(table);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints layoutCon = new GridBagConstraints();
        layoutCon.fill=GridBagConstraints.BOTH;
        layoutCon.gridx=0;
        layoutCon.gridy=0;
//        layoutCon.gridwidth=0;
//        layoutCon.gridheight=3;
        layoutCon.weightx=1;
        layoutCon.weighty=1;
        layout.setConstraints(selPanel,layoutCon);
        layoutCon.gridx=0;
        layoutCon.gridy=1;
//        layoutCon.gridwidth=0;
//        layoutCon.gridheight=7;
        layoutCon.weightx=1;
        layoutCon.weighty=0.7;
        layout.setConstraints(table,layoutCon);

        this.add(selPanel);
        this.add(table);
    }

}
