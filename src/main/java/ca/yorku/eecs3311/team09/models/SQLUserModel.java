package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A {@link IUserModel IUserModel} that stores user information in an SQL Database.
 * <p>
 * Uses an {@link IDBContext IDBContext} object to determine which database to store data in.
 */
public class SQLUserModel extends UserModel {
    protected IDBContext context;

    /**
     * Name of the table to store data in.
     */
    protected String tableName;
    /**
     * Name of the username column.
     */
    protected String idColumn;
    /**
     * Name of the password column.
     */
    protected String passwordColumn;

    /**
     * Returns a new SQLUserModel and creates the User table if it does not already exist.
     *
     * @param context database context object
     * @throws RuntimeException if the database file is not found.
     */
    public SQLUserModel(IDBContext context) throws RuntimeException {
        this.context = context;

        this.tableName = "USER";
        this.idColumn = "user_id";
        this.passwordColumn = "password";

        this.create();
    }

    @Override
    public void registerUser(String username, String password) throws RuntimeException {
        if (this.usernameExists(username)) {
            throw new UsernameTakenException("This username is already being used...");
        }

        String query = String.format(
                "INSERT INTO %s (%s, %s) VALUES(?, ?)",
                this.tableName, this.idColumn, this.passwordColumn
        );

        try (
                Connection conn = this.context.getConnection();
                PreparedStatement stmnt = conn.prepareStatement(query);
        ) {
            stmnt.setString(1, username);
            stmnt.setString(2, this.hashPassword(password));

            stmnt.executeUpdate();

            this.notifyRegistrationObservers();
        } catch (SQLException e) {
            throw new RuntimeException("database error: " + e);
        }
    }

    @Override
    public void loginUser(String username, String password) throws IncorrectCredentialsException {
        String query = String.format(
                "SELECT %s, %s FROM %s WHERE %s = ?",
                this.idColumn, this.passwordColumn, this.tableName, this.idColumn
        );

        boolean userFound = false;
        String hashedPassword = null;

        try (
                Connection conn = this.context.getConnection();
                PreparedStatement stmnt = conn.prepareStatement(query);
        ) {
            stmnt.setString(1, username);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                userFound = true;
                hashedPassword = rs.getString(this.passwordColumn);
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("database error: " + e);
        }

        if (userFound && this.checkPassword(password, hashedPassword)) {
            this.username = username;
            this.notifyLoginObservers();
        } else {
            throw new IncorrectCredentialsException("Username/Password is incorrect...");
        }
    }

    /**
     * Checks if a username is already registered in the database.
     *
     * @param username username to check
     * @return true if the username is registered, false otherwise.
     * @throws RuntimeException if database could not be accessed.
     */
    protected boolean usernameExists(String username) throws RuntimeException {
        boolean exists = false;

        String query = String.format(
                "SELECT %s, %s FROM %s WHERE %s = ?",
                this.idColumn, this.passwordColumn, this.tableName, this.idColumn
        );

        try (
                Connection conn = this.context.getConnection();
                PreparedStatement stmnt = conn.prepareStatement(query)
        ) {
            stmnt.setString(1, username);
            ResultSet rs = stmnt.executeQuery();

            exists = rs.next();

            rs.close();
        } catch (Exception e) {
            throw new RuntimeException("database error: " + e);
        }

        return exists;
    }

    /**
     * Creates a sql user table if it does not exist.
     *
     * @throws RuntimeException if the connection string is invalid.
     */
    protected void create() throws RuntimeException {
        String query = String.format(
                "CREATE TABLE IF NOT EXISTS %s (\n"
                        + "	%s VARCHAR PRIMARY KEY,\n"
                        + "	%s VARCHAR NOT NULL\n"
                        + ");",
                this.tableName, this.idColumn, this.passwordColumn
        );

        try (
                Connection conn = this.context.getConnection();
                PreparedStatement stmnt = conn.prepareStatement(query)
        ) {
            stmnt.executeUpdate();
        } catch (NullPointerException e) {
            throw new RuntimeException("Invalid connection string...");
        } catch (Exception e) {
            throw new RuntimeException("Could not access database...");
        }
    }
}
