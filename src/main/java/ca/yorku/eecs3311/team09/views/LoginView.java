package ca.yorku.eecs3311.team09.views;

import javax.swing.*;

// Using Singleton Design Pattern

public class LoginView extends JFrame {

    private static final LoginView LoginGui = new LoginView();

    protected JPanel loginPanel;

    protected JTextField usernameField;
    protected JTextField passwordField;

    protected JButton submitBtn;
    protected JButton registerBtn;

    private LoginView() {

        this.loginPanel = new JPanel();
        final JLabel userLabel = new JLabel("Username");
        this.usernameField = new JTextField();
        final JLabel passwordLabel = new JLabel("Password");
        this.passwordField = new JPasswordField();
        this.submitBtn = new JButton();
        this.registerBtn = new JButton();

        // JFrame loginFrame=new JFrame();
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel loginPanel=new JPanel();
        loginPanel.setLayout(null);
        this.add(loginPanel);

        // JLabel userLabel=new JLabel("Username");
        userLabel.setBounds(10, 100, 80, 25);
        loginPanel.add(userLabel);

        // JTextField usernameField=new JTextField(100);

        usernameField.setBounds(150, 100, 300, 25);
        loginPanel.add(usernameField);

        // JLabel passwordLabel= new JLabel("Password");
        passwordLabel.setBounds(10, 200, 80, 25);
        loginPanel.add(passwordLabel);

        // JPasswordField passwordField=new JPasswordField(100);
        passwordField.setBounds(150, 200, 300, 25);
        loginPanel.add(passwordField);

        // JButton submitBtn=new JButton();
        submitBtn.setBounds(350, 300, 75, 35);
        submitBtn.setText("Submit");
        // submitBtn.addActionListener(new LoginView());
        loginPanel.add(submitBtn);

        // JButton registerBtn=new JButton();
        registerBtn.setBounds(150, 300, 140, 35);
        registerBtn.setText("Register");
        loginPanel.add(registerBtn);

    }


    public JTextField getUsernameField() { // getter for username Jtext feild

        return this.usernameField;

    }

    public JTextField getPasswordField() { // getter for password Jtext feild

        return this.passwordField;

    }

    public JButton getSubmitBtn() { // getter for Submit button

        return this.submitBtn;

    }

    public JButton getRegisterBtn() { // getter for Register button

        return this.registerBtn;

    }

    public static LoginView getInstance() { // singleton design pattern get instance

        return LoginView.LoginGui;
    }


}
