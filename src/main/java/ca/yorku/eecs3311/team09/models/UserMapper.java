package ca.yorku.eecs3311.team09.models;

import java.util.List;

/**
 * Represents a strategy to get, put and drop {@link UserModel UserModels}
 * in the database.
 */
public interface UserMapper {

    /**
     * Returns all the UserModels in the database.
     *
     * @return all UserModels in the database.
     */
    public List<UserModel> getAllUsers();

    /**
     * Returns the UserModel with the given username.
     *
     * @param username username to query
     * @return UserModel with the given username; null if username is not present in the database
     */
    public UserModel getUser(String username);

    /**
     * Inserts the UserModel into the database iff the username is not present in the database.
     *
     * @param user UserModel to insert
     */
    public void putUser(UserModel user);

    /**
     * Drop UserModel from the database with the given username.
     *
     * @param username username to query
     */
    public void dropUser(String username);

    /**
     * Returns true if username is already in database, false otherwise.
     *
     * @param username username to query
     * @return true if username is already in database, false otherwise
     */
    public boolean usernameExists(String username);
}
