package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;

/**
 * Represents the user that is currently logged-in to the system.
 * Provides means to register and login a user to the system. This class
 * also notifies observers upon the successful registration or login of a
 * user.
 */
public interface IUserModel {
    /**
     * Returns the username of the current logged-in user.
     *
     * @return username of this user
     */
    String getUsername();

    /**
     * Registers a login observer.
     *
     * @param observer observer
     */
    void addLoginObserver(ILoginObserver observer);

    /**
     * Notifies all observers of a successful login.
     */
    void notifyLoginObservers();

    /**
     * Registers a registration observer.
     *
     * @param observer observer
     */
    void addRegistrationObserver(IRegistrationObserver observer);

    /**
     * Notifies all observers of a successful registration.
     */
    void notifyRegistrationObservers();

    /**
     * Registers this User into the system and notifies observers iff succeeded.
     *
     * @param username username
     * @param password password
     * @throws UsernameTakenException if the username is already taken
     */
    void registerUser(String username, String password) throws UsernameTakenException;

    /**
     * Attempts to log in with the provided credentials.
     *
     * @param username username
     * @param password password
     * @throws IncorrectCredentialsException if the username/password are not valid credentials
     */
    void loginUser(String username, String password) throws IncorrectCredentialsException;
}
