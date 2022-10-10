package ca.yorku.eecs3311.team09.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An Object Relation Mapper (ORM) for the UserModel class.
 * Represents a strategy to map UserModel to a SQLite Database.
 * <p>
 * The Database is location is determined by the {@link DBContext DBContext} connection string.
 */
public class SQLUserMapper implements UserMapper {
    protected String tableName;
    protected String idColumn;
    protected String passwordColumn;

    /**
     * Constructs a new SQLUserMapper.
     */
    public SQLUserMapper() {
        this.tableName = "USER";
        this.idColumn = "user_id";
        this.passwordColumn = "password";
        this.create();
    }


    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();

        String query = String.format(
                "SELECT %s, %s FROM %s",
                this.idColumn, this.passwordColumn, this.tableName
        );
        try (
                Connection conn = this.connect();
                PreparedStatement stmnt = conn.prepareStatement(query);
                ResultSet rs = stmnt.executeQuery();
        ) {
            while (rs.next()) {
                UserModel user = new UserModel();

                user.username = rs.getString(this.idColumn);
                user.hashedPassword = rs.getString(this.passwordColumn);

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public UserModel getUser(String username) {

        UserModel user = null;

        String query = String.format(
                "SELECT %s, %s FROM %s WHERE %s = ?",
                this.idColumn, this.passwordColumn, this.tableName, this.idColumn
        );

        try (
                Connection conn = this.connect();
                PreparedStatement stmnt = conn.prepareStatement(query);
        ) {
            stmnt.setString(1, username);
            ResultSet rs = stmnt.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.username = rs.getString(this.idColumn);
                user.hashedPassword = rs.getString(this.passwordColumn);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public void putUser(UserModel user) {
        if (!usernameExists(user.getUsername())) {
            String query = String.format(
                    "INSERT INTO %s (%s, %s) VALUES(?, ?)",
                    this.tableName, this.idColumn, this.passwordColumn
            );

            try (
                    Connection conn = this.connect();
                    PreparedStatement stmnt = conn.prepareStatement(query);
            ) {
                stmnt.setString(1, user.getUsername());
                stmnt.setString(2, user.getHashedPassword());

                stmnt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("User already exists");
        }
    }

    @Override
    public void dropUser(String username) {
        if (usernameExists(username)) {
            String query = String.format(
                    "DELETE FROM %s WHERE %s = ?",
                    this.tableName, this.idColumn
            );

            try (
                    Connection conn = this.connect();
                    PreparedStatement stmnt = conn.prepareStatement(query);
            ) {
                stmnt.setString(1, username);

                stmnt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("User does not exist!");
        }
    }

    @Override
    public boolean usernameExists(String username) {
        return this.getUser(username) != null;
    }

    /**
     * Creates a connection to the database.
     *
     * @return connection to the database.
     */
    private Connection connect() {
        Connection conn = null;
        DBContext context = DBContext.getInstance();
        try {
            conn = DriverManager.getConnection(context.getConnectionString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * A helper method that creates the table (if it doesn't already exist) for UserModel data.
     */
    private void create() {
        String query = String.format(
                "CREATE TABLE IF NOT EXISTS %s (\n"
                        + "	%s VARCHAR PRIMARY KEY,\n"
                        + "	%s VARCHAR NOT NULL\n"
                        + ");",
                this.tableName, this.idColumn, this.passwordColumn
        );

        try (
                Connection conn = this.connect();
                PreparedStatement stmnt = conn.prepareStatement(query);
        ) {
            stmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
