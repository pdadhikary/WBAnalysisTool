package ca.yorku.eecs3311.team09.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Using Singleton Design Pattern

public class LoginView extends JFrame {

	private static final LoginView LoginGui = new LoginView();

	protected JPanel loginpanel;

	protected JTextField usernameFeild;
	protected JTextField passwordFeild;
	
	protected JButton submitBtn;
	protected JButton registerBtn;

	private LoginView() {
		
		this.loginpanel = new JPanel();
		final JLabel userlabel = new JLabel("Username");
		this.usernameFeild = new JTextField();
		final JLabel passwordlabel = new JLabel("Password");
		this.passwordFeild = new JPasswordField();
		this.submitBtn = new JButton();
		this.registerBtn = new JButton();

		// JFrame loginframe=new JFrame();
		this.setSize(550, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel loginpanel=new JPanel();
		loginpanel.setLayout(null);
		this.add(loginpanel);

		// JLabel userlabel=new JLabel("Username");
		userlabel.setBounds(10, 100, 80, 25);
		loginpanel.add(userlabel);

		// JTextField usernameFeild=new JTextField(100);

		usernameFeild.setBounds(150, 100, 300, 25);
		loginpanel.add(usernameFeild);

		// JLabel passwordlabel= new JLabel("Password");
		passwordlabel.setBounds(10, 200, 80, 25);
		loginpanel.add(passwordlabel);

		// JPasswordField passwordFeild=new JPasswordField(100);
		passwordFeild.setBounds(150, 200, 300, 25);
		loginpanel.add(passwordFeild);

		// JButton submitBtn=new JButton();
		submitBtn.setBounds(350, 300, 75, 35);
		submitBtn.setText("Submit");
		// submitBtn.addActionListener(new LoginView());
		loginpanel.add(submitBtn);

		// JButton registerBtn=new JButton();
		registerBtn.setBounds(150, 300, 140, 35);
		registerBtn.setText("Register");
		loginpanel.add(registerBtn);
	
	}

	
	public JTextField getusernameFeild() { // getter for username Jtext feild

		return this.usernameFeild;

	}

	public JTextField getpasswordFeild() { // getter for password Jtext feild

		return this.passwordFeild;

	}

	public JButton getsubmitBtn() { // getter for Submit button

		return this.submitBtn;

	}

	public JButton getregisterBtn() { // getter for Register button

		return this.registerBtn;

	}

	public static LoginView getInstance() { // singleton design pattern get instance

		return LoginView.LoginGui;
	}


}
