package ca.yorku.eecs3311.team09.models;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides the necessary context and connection for Database operation in a Model.
 */
public interface IDBContext {
    /**
     * Sets the connection string of the Context.
     *
     * @param connectionString connection string
     */
    void setConnectionString(String connectionString);

    /**
     * Returns a connection to the database.
     *
     * @return connection to the database, null if connection string is null.
     * @throws SQLException if database is not found.
     */
    Connection getConnection() throws SQLException;
}
