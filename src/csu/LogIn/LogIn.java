package csu.LogIn;

import csu.MainWindow.SalControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn {
    public static void main(String[] args) {
        JFrame logInWin = new JFrame("��¼");
        //���ô�С
        logInWin.setSize(400, 300);
        //������Ե�λ��:������ʾ
        logInWin.setLocationRelativeTo(null);

        JButton logIn = new JButton("��¼");
        logInWin.add(logIn);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action Recived");

                SalControl salControl = new SalControl();

                //salControl.setLocationRelativeTo(null);

                salControl.setVisible(true);
                logInWin.setVisible(false);

            }
        });


        logInWin.setVisible(true);
    }


}
