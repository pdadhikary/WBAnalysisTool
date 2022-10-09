package ca.yorku.eecs3311.team09.views;

import javax.swing.*;

public class LoginError {
    public LoginError() {


        JFrame ErrorFrame = new JFrame();
        ErrorFrame.setSize(550, 400);
        ErrorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(null);
        ErrorFrame.add(errorPanel);

        JLabel errormessage = new JLabel("Wrong Username/Password");
        errormessage.setBounds(175, 170, 200, 60);
        errorPanel.add(errormessage);

        ErrorFrame.setVisible(true);

    }

}
