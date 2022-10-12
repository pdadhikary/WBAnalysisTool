package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;

import java.sql.SQLException;

/**
 * Represents a User Entity of the application.
 */
public interface IUserModel {
    /**
     * Sets the username of this User.
     *
     * @param username username
     */
    void setUsername(String username);

    /**
     * Returns the username of this User.
     *
     * @return username of this user
     */
    String getUsername();

    /**
     * Sets the password of this user. The raw text password will be hashed before it is set.
     *
     * @param password raw text password
     */
    void setPassword(String password);

    /**
     * Registers a login observer.
     *
     * @param observer observer
     */
    void addLoginObserver(ILoginObserver observer);

    /**
     * Notifies all observers of a successful login
     */
    void notifyLoginObservers();

    /**
     * Registers a Registration Observer.
     *
     * @param observer observer
     */
    void addRegistrationObserver(IRegistrationObserver observer);

    /**
     * Notifies all observers of a successful registration
     */
    void notifyRegistrationObservers();

    /**
     * Registers this User into the database and notifies observers upon success.
     *
     * @throws UsernameTakenException if the username is already taken
     * @throws SQLException           if a database exception occurs
     */
    void registerUser() throws UsernameTakenException, SQLException;

    /**
     * Attempts to log in with the provided credentials.
     *
     * @param username username
     * @param password password
     * @throws IncorrectCredentialsException if the username/password are not valid credentials
     * @throws SQLException                  if a database exception occurs
     */
    void loginUser(String username, String password) throws IncorrectCredentialsException, SQLException;
}
