package Power;

import javax.swing.*;


public class MainFrame extends JFrame {

   JMenuBar menuBar = new JMenuBar();
//员工面板呈现
  public  void initContent()
  {
     empPanel panel = new empPanel();
     panel.initData();

     setContentPane(panel);
  }

//  管理员查询面板呈现
public  void initContent1()
{
    empPanel panel = new empPanel();
    panel.initData1();
//    setContentPane(panel);
}
//管理员增加工资项目
   public  void initContent2()
 {
    empPanel panel = new empPanel();
    panel.initData2();
    setContentPane(panel);
 }
 //管理员删除工资项目
    public  void initContent3()
    {
        empPanel panel = new empPanel();
        panel.initData3();
        setContentPane(panel);
    }

}
