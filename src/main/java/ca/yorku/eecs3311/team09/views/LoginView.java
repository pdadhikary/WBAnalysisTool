package ca.yorku.eecs3311.team09.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Using Singleton Design Pattern

public class LoginView implements ActionListener {

    private static final LoginView LoginGui=new LoginView();





    private JFrame loginframe=new JFrame();
    private  JPanel loginpanel=new JPanel();
    private  JLabel userlabel=new JLabel("Username");
    private  JTextField usernameFeild=new JTextField(100);
    private  JLabel passwordlabel= new JLabel("Password");
    private  JPasswordField passwordFeild=new JPasswordField(100);
    private  JButton submitBtn=new JButton();
    private  JButton registerBtn=new JButton();

    private LoginView(){


       // JFrame loginframe=new JFrame();
        loginframe.setSize(550,400);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       // JPanel loginpanel=new JPanel();
        loginpanel.setLayout(null);
        loginframe.add(loginpanel);

       // JLabel userlabel=new JLabel("Username");
        userlabel.setBounds(10,100,80,25);
        loginpanel.add(userlabel);

           // JTextField usernameFeild=new JTextField(100);

            usernameFeild.setBounds(150,100,300,25);
            loginpanel.add(usernameFeild);


          //  JLabel passwordlabel= new JLabel("Password");
            passwordlabel.setBounds(10,200,80,25);
            loginpanel.add(passwordlabel);

           // JPasswordField passwordFeild=new JPasswordField(100);
            passwordFeild.setBounds(150,200,300,25);
            loginpanel.add(passwordFeild);

           // JButton submitBtn=new JButton();
            submitBtn.setBounds(350,300,75,35);
            submitBtn.setText("Submit");
           // submitBtn.addActionListener(new LoginView());
            loginpanel.add(submitBtn);




       // JButton registerBtn=new JButton();
        registerBtn.setBounds(150,300,140,35);
        registerBtn.setText("Register");
        loginpanel.add(registerBtn);
        loginframe.setVisible(true);
    }




    public String getPasswordtext(){ // getter for password text

        return passwordFeild.getPassword().toString();


    }

    public String getUsernametext(){ // getter for username text

        return usernameFeild.getText();

    }

    public JTextField getusernameFeild(){ // getter for username Jtext feild

        return usernameFeild;


    }


    public JTextField getpasswordFeild(){  // getter for password  Jtext feild

        return passwordFeild;


    }

    public JButton getsubmitBtn(){  // getter for Submit button

        return submitBtn;


    }


    public JButton getregisterBtn(){ // getter for Register button

        return registerBtn;


    }



    public static LoginView getInstance(){  // singleton design pattern get instance

        return LoginGui;
    }





    public static void main (String[] args){
        LoginView main= new LoginView();


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
