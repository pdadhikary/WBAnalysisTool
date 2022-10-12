package ca.yorku.eecs3311.team09.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton class that provides the necessary context and connection for Database operation in a Model.
 */
public class DBContext implements IDBContext {
    /**
     * Connection string to SQLite database.
     */
    public static final String DEFAULT_DB = "jdbc:sqlite:src/main/resources/database/user.db";
    /**
     * Connection string to SQLite test database.
     */
    public static final String DEFAULT_TEST_DB = "jdbc:sqlite:src/main/resources/database/test/user_test.db";
    protected String connectionString;
    private static final DBContext instance = new DBContext();

    /**
     * Constructs a DBContext object.
     */
    private DBContext() {
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connectionString == null) {
            return null;
        }

        return DriverManager.getConnection(this.connectionString);
    }

    /**
     * Returns the instance of DBContext.
     *
     * @return DBContext
     */
    public static DBContext getInstance() {
        return DBContext.instance;
    }
}
