package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IUserModel;
import ca.yorku.eecs3311.team09.views.AppView;
import ca.yorku.eecs3311.team09.views.LoginView;

/**
 * Defines the strategy to handle UI interactions of the {@link LoginView LoginView}.
 * Uses the {@link IUserModel IUserModel} interface to register and login users to the system.
 */
public class LoginController implements ILoginController, ILoginObserver {
    protected IUserModel model;
    protected LoginView view;

    /**
     * Returns a new login controller.
     *
     * @param model the user model
     */
    public LoginController(IUserModel model) {
        this.model = model;
        this.model.addLoginObserver(this);

        this.view = new LoginView(model, this);
    }

    /**
     * Registers a user to the system.
     * <p>
     * A {@link ValidationException ValidationException} will be thrown if:
     * <p>
     * 1. The username is null.
     * <p>
     * 2. The username is an empty string.
     * <p>
     * 3. The username contains spaces.
     * <p>
     * 4. The password is null.
     * <p>
     * 5. The password is an empty string.
     *
     * @param username username
     * @param password password
     * @throws ValidationException    if the username and password do not meet the validation criteria.
     * @throws UsernameTakenException if the username is already registered on the system.
     */
    @Override
    public void register(String username, String password) throws ValidationException, UsernameTakenException {
        // Validate username
        FormValidationUtility.checkNotNull(username, "username");
        FormValidationUtility.checkNotEmpty(username, "username");
        FormValidationUtility.notContainSpace(username, "username");
        // Validate password
        FormValidationUtility.checkNotNull(password, "password");
        FormValidationUtility.checkNotEmpty(password, "password");

        this.model.registerUser(username, password);
    }

    @Override
    public void submit(String username, String password) throws IncorrectCredentialsException {
        this.model.loginUser(username, password);
    }

    @Override
    public void showLoginView() {
        this.view.setVisible(true);
    }

    @Override
    public void disposeLoginView() {
        this.view.dispose();
    }

    /**
     * @deprecated Displays the {@link AppView AppView} upon successful user login.
     */
    @Override
    public void successfulLogin() {
        // TODO: refactor code to AppController
//        AppView app = new AppView();
//        app.setVisible(true);
    }
}
