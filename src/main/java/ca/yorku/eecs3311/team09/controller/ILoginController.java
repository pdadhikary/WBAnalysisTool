package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.views.LoginView;

/**
 * A control interface that provides strategies to implement user interactions on the {@link LoginView LoginView}.
 */
public interface ILoginController {
    /**
     * Attempts to register a user to system.
     *
     * @param username username
     * @param password password
     * @throws ValidationException    if the username and password do not meet the validation criteria.
     * @throws UsernameTakenException if the username is already registered on the system.
     */
    void register(String username, String password) throws ValidationException, UsernameTakenException;

    /**
     * Attempts to log in a user to the system.
     *
     * @param username username
     * @param password password
     * @throws IncorrectCredentialsException if the username is not found in the system or the password is incorrect.
     */
    void submit(String username, String password) throws IncorrectCredentialsException;

    /**
     * Display the {@link LoginView LoginView}
     */
    void showLoginView();

    /**
     * Close the {@link LoginView LoginView}
     */
    void disposeLoginView();
}
