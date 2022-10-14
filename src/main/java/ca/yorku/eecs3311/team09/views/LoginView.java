package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IRegistrationObserver;
import ca.yorku.eecs3311.team09.models.IUserModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener, IRegistrationObserver, ILoginObserver {
    public static final String LOGO_URI = "src/main/resources/static/image/wb_logo.png";

    protected IUserModel model;
    protected ILoginController controller;

    protected JPanel loginPanel;
    protected JTextField usernameField;
    protected JTextField passwordField;
    protected JButton submitBtn;
    protected JButton registerBtn;

    public LoginView(IUserModel model, ILoginController controller) {

        this.controller = controller;
        this.model = model;

        // Register as observers
        model.addLoginObserver(this);
        model.addRegistrationObserver(this);

        // panel settings
        this.loginPanel = new JPanel();
        loginPanel.setLayout(null);

        // frame settings
        this.setTitle("Login");
        this.setIconImage(new ImageIcon(LoginView.LOGO_URI).getImage());
        this.setSize(550, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create UI Elements
        this.createFields();
        this.createButtons();

        this.add(loginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();

        try {
            if (e.getSource().equals(this.registerBtn)) {
                controller.register(username, password);
            } else if (e.getSource().equals(this.submitBtn)) {
                controller.submit(username, password);
            }
        } catch (ValidationException | IncorrectCredentialsException | UsernameTakenException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Internal error occurred...",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void successfulLogin() {
        this.dispose();
        JOptionPane.showMessageDialog(
                null,
                "Welcome, " + this.model.getUsername() + "!",
                "Successfully Logged In",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void successfulRegistration() {
        JOptionPane.showMessageDialog(
                this,
                "Registration was successful!",
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    protected void createFields() {
        // username label and field
        final JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 100, 80, 25);
        loginPanel.add(userLabel);
        this.usernameField = new JTextField();
        usernameField.setBounds(150, 100, 300, 25);
        loginPanel.add(usernameField);

        // password label and field
        final JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 200, 80, 25);
        loginPanel.add(passwordLabel);
        this.passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 300, 25);
        loginPanel.add(passwordField);
    }

    protected void createButtons() {
        // submit button
        this.submitBtn = new JButton("Submit");
        submitBtn.setBounds(350, 300, 75, 35);
        this.submitBtn.addActionListener(this);
        loginPanel.add(submitBtn);

        // register button
        this.registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 300, 140, 35);
        this.registerBtn.addActionListener(this);
        loginPanel.add(registerBtn);
    }
}
