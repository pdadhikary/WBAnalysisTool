package ca.yorku.eecs3311.team09.models;

/**
 * A singleton class that provides the necessary context for Database operation in the program.
 * <p>
 * Uses {@link UserMapper UserMapper} instance to set the strategy for {@link UserModel UserModel} database
 * operations.
 */
public class DBContext {
    /**
     * Connection string to SQLite database.
     */
    public static final String DEFAULT_DB = "jdbc:sqlite:src/main/resources/database/user.db";
    protected String connectionString;
    protected UserMapper userMapper;
    private static final DBContext INSTANCE = new DBContext();

    /**
     * Constructs a DBContext object.
     */
    private DBContext() {
        this.connectionString = DBContext.DEFAULT_DB;
        this.userMapper = new SQLUserMapper();
    }

    /**
     * Returns the connection string to the database.
     *
     * @return connection string to the database
     */
    public String getConnectionString() {
        return this.connectionString;
    }

    /**
     * Sets the connection string of the Context.
     *
     * @param connectionString connection string
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Gets the current UserMapper strategy for the Context.
     *
     * @return current {@link UserMapper UserMapper} strategy
     */
    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    /**
     * Sets the current UserMapper strategy for the Context.
     *
     * @param mapper {@link UserMapper UserMapper} strategy
     */
    public void setUserMapper(Class<? extends UserMapper> mapper) {
        try {
            this.userMapper = mapper.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the instance of DBContext.
     *
     * @return DBContext
     */
    public static DBContext getInstance() {
        return DBContext.INSTANCE;
    }
}
