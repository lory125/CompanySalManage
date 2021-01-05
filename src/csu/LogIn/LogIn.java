package csu.LogIn;

import csu.MainWindow.SalControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn {
    public static void main(String[] args) {
        JFrame logInWin = new JFrame("登录");
        //设置大小
        logInWin.setSize(400, 300);
        //设置相对的位置:居中显示
        logInWin.setLocationRelativeTo(null);

        JButton logIn = new JButton("登录");
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
