package ca.yorku.eecs3311.team09.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

/**
 * Represents a User Entity of the application.
 * <p>
 * Provides static utility function to get, insert and drop Users from the database.
 * <p>
 * These utility functions are a Facade for the current instance of
 * {@link UserMapper UserMapper}
 * instantiated in the {@link DBContext DBContext} to accomplish
 * the database access methods.
 */
public class UserModel {
    protected String username;
    protected String hashedPassword;

    /**
     * Protected constructor used only by the UserMapper for
     * data retrieval from the database.
     */
    protected UserModel() {
        this.username = "";
        this.hashedPassword = "";
    }

    /**
     * Constructs a new UserModel with the given username and password.
     * Note, that only the hashed password can be retrieved.
     *
     * @param username username of the User
     * @param password raw text password of the User
     */
    public UserModel(String username, String password) {
        this.username = username;
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Returns the username of this UserModel.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the hashed password of this UserModel.
     *
     * @return hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Checks whether the provided raw text password belongs this UserModel.
     *
     * @param password rew text password to check.
     * @return true if the password belongs to this UserModel, false otherwise.
     */
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
    }

    /**
     * Returns true if Object is a UserModel with the same username as this UserModel, false otherwise.
     *
     * @param o Object to compare
     * @return true if Object is a UserModel with the same username as this UserModel, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return this.username.equals(userModel.getUsername());
    }

    /**
     * Returns the hash code of this UserModel
     *
     * @return hash code of this UserModel
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns a list of all the UserModels registered in the database.
     *
     * @return all the UserModels in the database
     */
    public static List<UserModel> getAll() {
        return DBContext.getInstance().getUserMapper().getAllUsers();
    }

    /**
     * Returns the UserModel retrieved with the provided username.
     *
     * @param username username to query
     * @return UserModel with the provided username. Null if no UserModel
     * is registered with the provided username.
     */
    public static UserModel get(String username) {
        return DBContext.getInstance().getUserMapper().getUser(username);
    }

    /**
     * Registers a UserModel into the database iff the username is not already taken.
     *
     * @param user UserModel to register.
     */
    public static void register(UserModel user) {
        DBContext.getInstance().getUserMapper().putUser(user);
    }

    /**
     * Unregister the UserModel with the provided username.
     *
     * @param username username of the UserModel to unregister.
     */
    public static void unregister(String username) {
        DBContext.getInstance().getUserMapper().dropUser(username);
    }

    /**
     * Checks if the username is already registered.
     *
     * @param username username to check
     * @return true is the username is in user, false otherwise.
     */
    public static boolean usernameRegistered(String username) {
        return DBContext.getInstance().getUserMapper().usernameExists(username);
    }
}
