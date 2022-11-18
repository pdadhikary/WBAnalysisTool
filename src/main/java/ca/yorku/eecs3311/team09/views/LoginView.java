package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The login GUI.
 */
public class LoginView extends JFrame implements ActionListener {
    /**
     * path to the logo for this app.
     */
    public static final String LOGO_URI = "src/main/resources/static/image/wb_logo.png";

    /**
     * controller of this view.
     */
    protected ILoginController controller;
    /**
     * panel containing the fields, labels and buttons.
     */
    protected JPanel loginPanel;
    /**
     * username field
     */
    protected JTextField usernameField;
    /**
     * password field
     */
    protected JTextField passwordField;
    /**
     * submit button
     */
    protected JButton submitBtn;
    /**
     * register button
     */
    protected JButton registerBtn;

    /**
     * Returns a new LoginView
     *
     * @param controller the controller of this view
     */
    public LoginView(ILoginController controller) {

        this.controller = controller;

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

    public void showRegistrationSuccess() {
        JOptionPane.showMessageDialog(
                this,
                "Registration was successful!",
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Creates all the input fields and labels for this view.
     */
    protected void createFields() {
        // username label and field
        final JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 100, 80, 25);
        loginPanel.add(userLabel);
        this.usernameField = new JTextField();
        usernameField.setBounds(150, 100, 300, 25);
        loginPanel.add(usernameField);

        // password label and field
        final JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 200, 80, 25);
        loginPanel.add(passwordLabel);
        this.passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 300, 25);
        loginPanel.add(passwordField);
    }

    /**
     * Creates all the buttons for this view.
     */
    protected void createButtons() {
        // submit button
        this.submitBtn = new JButton("Submit");
        submitBtn.setBounds(350, 300, 90, 35);
        this.submitBtn.addActionListener(this);
        loginPanel.add(submitBtn);

        // register button
        this.registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 300, 130, 35);
        this.registerBtn.addActionListener(this);
        loginPanel.add(registerBtn);
    }
}
