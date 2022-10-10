package ca.yorku.eecs3311.team09.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserWriter implements UserWriter {
    public static final String DEFAULT_DB = "jdbc:sqlite:src/main/resources/database/user.db";
    protected String connectionString;
    protected String tableName;
    protected String idColumn;
    protected String passwordColumn;

    public SQLUserWriter(String connectionString) {
        this.connectionString = connectionString;
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
            System.out.println("error");
        }
        return users;
    }

    @Override
    public UserModel getUser(String username) {

        UserModel user = null;

        String query = String.format(
                "SELECT %s, %s FROM %s WHERE %s=?",
                this.idColumn, this.passwordColumn, this.tableName, this.idColumn
        );

        try (
                Connection conn = this.connect();
                PreparedStatement stmnt = conn.prepareStatement(query);
        ) {
            stmnt.setString(0, username);
            ResultSet rs = stmnt.executeQuery();

            if (rs.first()) {
                user = new UserModel();
                user.username = rs.getString(this.idColumn);
                user.hashedPassword = rs.getString(this.passwordColumn);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("error");
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
                stmnt.setString(0, user.getUsername());
                stmnt.setString(1, user.getHashedPassword());

                stmnt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error");
            }

        } else {
            System.out.println("User already exists");
        }
    }

    @Override
    public boolean usernameExists(String username) {
        return this.getUser(username) != null;
    }

    private Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(this.connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private void create() {
        String query = String.format(
                "CREATE TABLE IF NOT EXISTS warehouses (\n"
                        + "	%s VARCHAR,\n"
                        + "	%s VARCHAR\n"
                        + ");",
                this.idColumn, this.passwordColumn
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
