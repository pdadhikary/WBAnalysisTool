package ca.yorku.eecs3311.team09.views;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LoginError {


    public LoginError() {


    JFrame errorframe = new JFrame();
        errorframe.setSize(550,400);
        errorframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel errorpanel = new JPanel();
        errorpanel.setLayout(null);
        errorframe.add(errorpanel);

        JLabel errormessage= new JLabel("Wrong Username/Password");
        errormessage.setBounds(175,170,200,60);
        errorpanel.add(errormessage);

        errorframe.setVisible(true);

}
    public static void main (String[] args){
        LoginError main= new LoginError();


    }

}
