package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A {@link UserModel UserModel} that stores user information in a SQL Database.
 * <p>
 * Uses {@link IDBContext IDBContext} object to determine which database to store data in.
 */
public class SQLUserModel extends UserModel {
    protected IDBContext context;

    // Table information
    protected String tableName;
    protected String idColumn;
    protected String passwordColumn;

    public SQLUserModel(IDBContext context) throws RuntimeException {
        this.context = context;

        this.tableName = "USER";
        this.idColumn = "user_id";
        this.passwordColumn = "password";

        this.create();
    }

    @Override
    public void registerUser() throws RuntimeException, SQLException {
        if (!this.usernameExists(this.username)) {
            String query = String.format(
                    "INSERT INTO %s (%s, %s) VALUES(?, ?)",
                    this.tableName, this.idColumn, this.passwordColumn
            );


            Connection conn = this.context.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(query);

            stmnt.setString(1, this.username);
            stmnt.setString(2, this.hashedPassword);

            stmnt.executeUpdate();
            stmnt.close();
            conn.close();

            this.notifyRegistrationObservers();

        } else {
            throw new UsernameTakenException("This username is already being used...");
        }
    }

    @Override
    public void loginUser(String username, String password) throws IncorrectCredentialsException, SQLException {
        String query = String.format(
                "SELECT %s, %s FROM %s WHERE %s = ?",
                this.idColumn, this.passwordColumn, this.tableName, this.idColumn
        );

        boolean userFound = false;


        Connection conn = this.context.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(query);

        stmnt.setString(1, username);
        ResultSet rs = stmnt.executeQuery();

        if (rs.next()) {
            userFound = true;
            this.username = rs.getString(this.idColumn);
            this.hashedPassword = rs.getString(this.passwordColumn);
        }

        rs.close();
        stmnt.close();
        conn.close();

        if (userFound && this.checkPassword(password)) {
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
            throw new RuntimeException("Could not access database...");
        }

        return exists;
    }

    /**
     * Creates a sql User table if it does not exist.
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
