package Power;

import javax.swing.*;


public class MainFrame extends JFrame {

   JMenuBar menuBar = new JMenuBar();
//Ա��������
  public  void initContent()
  {
     empPanel panel = new empPanel();
     panel.initData();

     setContentPane(panel);
  }

//  ����Ա��ѯ������
public  void initContent1()
{
    empPanel panel = new empPanel();
    panel.initData1();
//    setContentPane(panel);
}
//����Ա���ӹ�����Ŀ
   public  void initContent2()
 {
    empPanel panel = new empPanel();
    panel.initData2();
    setContentPane(panel);
 }
 //����Աɾ��������Ŀ
    public  void initContent3()
    {
        empPanel panel = new empPanel();
        panel.initData3();
        setContentPane(panel);
    }

}
