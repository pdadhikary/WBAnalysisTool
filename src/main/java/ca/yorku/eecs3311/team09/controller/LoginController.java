package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IUserModel;
import ca.yorku.eecs3311.team09.views.AppView;
import ca.yorku.eecs3311.team09.views.LoginView;

import java.sql.SQLException;

public class LoginController implements ILoginController, ILoginObserver {
    protected IUserModel model;
    protected LoginView view;

    public LoginController(IUserModel model) {
        this.model = model;
        this.model.addLoginObserver(this);

        this.view = new LoginView(model, this);

        this.view.setVisible(true);
    }

    @Override
    public void register(String username, String password) throws ValidationException, UsernameTakenException, SQLException {
        // Validate data
        FormValidationUtility.checkNotNull(username, "username");
        FormValidationUtility.checkNotEmpty(username, "username");
        FormValidationUtility.notContainSpace(username, "username");

        FormValidationUtility.checkNotNull(password, "password");
        FormValidationUtility.checkNotEmpty(password, "password");

        this.model.setUsername(username);
        this.model.setPassword(password);
        this.model.registerUser();
    }

    @Override
    public void submit(String username, String password) throws IncorrectCredentialsException, SQLException {
        this.model.loginUser(username, password);
    }

    @Override
    public void successfulLogin() {
        // TODO: refactor code AppController
        AppView app = new AppView();
        app.setVisible(true);
    }
}
