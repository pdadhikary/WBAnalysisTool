package ca.yorku.eecs3311.team09.models;

public class DBContext {
    private static final DBContext INSTANCE = new DBContext();
    public static final String DEFAULT_DB = "jdbc:sqlite:src/main/resources/database/user.db";
    protected String connectionString;
    protected UserMapper userMapper;

    private DBContext() {
        this.connectionString = DBContext.DEFAULT_DB;
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    public void setUserMapper(Class<? extends UserMapper> mapper) {
        try {
            this.userMapper = mapper.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DBContext getInstance() {
        return DBContext.INSTANCE;
    }
}
