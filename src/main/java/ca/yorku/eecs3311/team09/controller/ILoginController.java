package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;

import java.sql.SQLException;

public interface ILoginController {
    void register(String username, String password) throws ValidationException, UsernameTakenException, SQLException;

    void submit(String username, String password) throws IncorrectCredentialsException, SQLException;

    void showLoginView();

    void disposeLoginView();
}
