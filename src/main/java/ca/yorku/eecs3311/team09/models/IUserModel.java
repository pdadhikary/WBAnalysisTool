package ca.yorku.eecs3311.team09.models;

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
     */
    void registerUser() throws RuntimeException;

    /**
     * Attempts to log in this User and notifies observers upon success.
     */
    void loginUser() throws RuntimeException;
}
