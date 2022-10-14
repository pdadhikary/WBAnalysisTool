package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.models.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class SQLUserModelTest implements ILoginObserver, IRegistrationObserver {
    private static final String PATH_TO_DB = "src/main/resources/database/test/user_test.db";
    private static final String DB_CONNECTOR = "jdbc:sqlite";
    private static final String CONNECTION_STRING = String.join(":", new String[]{DB_CONNECTOR, PATH_TO_DB});

    private static final String TABLE_NAME = "USER";
    private static final String ID_COLUMN = "user_id";
    private static final String PASSWORD_COLUMN = "password";

    // USED for TESTING
    private Boolean loginSuccess;
    private Boolean registerSuccess;

    @BeforeClass
    public static void initialize() {
        DBContext.getInstance().setConnectionString(CONNECTION_STRING);
    }

    @Before
    public void before() {
        refreshDB();

        this.loginSuccess = false;
        this.registerSuccess = false;
    }

    /**
     * @testId DBContextTest01
     * @category Tests DBContext connection.
     * @testCoverage UC1-Database-DBContext
     * @precondition user_test.db is present in src/main/resources/database/test/
     * @procedure Steps required for this test:
     * 1. Create a test database (automated).
     * 2. Set DBContext connection string to point to the test database.
     * 3. Call getConnection() method on the DBContext instance.
     * @expectedOutcome returns connection to the test database.
     */
    @Test
    public void DBContextTest01() throws SQLException {
        Connection conn = DBContext.getInstance().getConnection();

        assertNotNull("Could not connect to the database", conn);
    }

    /**
     * @testId SQLUserModelTest01
     * @category Tests user registration into database.
     * @testCoverage UC1-User-Registration-SQLUserModel
     * @precondition user_test.db is present in src/main/resources/database/test/ & USER table is present
     * @procedure Steps required for this test:
     * 1. Create a test database (automated).
     * 2. Create USER table if not present (automated).
     * 3. Initialize SQLUserModel with a IDBContext instance.
     * 4. Set username and password.
     * 5. Call registerUser() method on the UserModel.
     * @expectedOutcome the SuccessfulRegistrationObservers get notified and the user is registered.
     */
    @Test
    public void SQLUserModelTest01() throws SQLException {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        // Become a registration observer
        model.addRegistrationObserver(this);

        model.registerUser(username, password);

        assertTrue("User was not registered!", this.registerSuccess);
    }

    /**
     * @testId SQLUserModelTest02
     * @category Tests user registration error into database.
     * @testCoverage UC1-User-Registration-Error-SQLUserModel
     * @precondition The user already exists in the database.
     * @procedure Steps required for this test:
     * 1. Create a test database (automated).
     * 2. Create USER table if not present (automated).
     * 3. Initialize SQLUserModel with a IDBContext instance.
     * 4. Insert the user into the database.
     * 5. Try and register the user again in the database.
     * @expectedOutcome throws an UsernameTakenException.
     */
    @Test(expected = UsernameTakenException.class)
    public void SQLUserModelTest02() throws SQLException {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        insertUser(username, password);

        model.registerUser(username, "RandomOtherPassword");
    }

    /**
     * @testId SQLUserModelTest03
     * @category Tests user login.
     * @testCoverage UC1-User-Login-SQLUserModel
     * @precondition The user already exists in the database.
     * @procedure Steps required for this test:
     * 1. Create a test database (automated).
     * 2. Create USER table if not present (automated).
     * 3. Initialize SQLUserModel with a IDBContext instance.
     * 4. Insert the user into the database.
     * 5. Try and login the user again.
     * @expectedOutcome the SuccessfulLoginObservers get notified and the user is logged in.
     */
    @Test
    public void SQLUserModelTest03() throws SQLException {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        model.addLoginObserver(this);

        insertUser(username, password);

        model.loginUser(username, password);

        assertTrue("User was not logged in!", this.loginSuccess);
    }

    /**
     * @testId SQLUserModelTest04
     * @category Tests user login error.
     * @testCoverage UC1-User-Login-Error-SQLUserModel
     * @precondition The user does not exist or the password does not match.
     * @procedure Steps required for this test:
     * 1. Create a test database (automated).
     * 2. Create USER table if not present (automated).
     * 3. Initialize SQLUserModel with a IDBContext instance.
     * * 4. Insert the user into the database.
     * 5. Try and login the user with incorrect username/password.
     * @expectedOutcome throws an IncorrectCredentialsException.
     */
    @Test(expected = IncorrectCredentialsException.class)
    public void SQLUserModelTest04() throws SQLException {
        String username = "user1";
        String password = "userpass";
        String incorrectPassword = "wrongpassword";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        insertUser(username, password);

        model.loginUser(username, incorrectPassword);
    }


    private static void refreshDB() {
        File dbFile = new File(PATH_TO_DB);
        boolean dirCreated = dbFile.getParentFile().mkdirs();
        try {
            boolean dbFileDeleted = dbFile.delete();
            boolean dbFileCreate = dbFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertUser(String username, String password) {
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());

        String query = String.format(
                "INSERT INTO %s (%s, %s) VALUES (? , ?)",
                TABLE_NAME, ID_COLUMN, PASSWORD_COLUMN
        );

        try (
                Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement stmnt = conn.prepareStatement(query)
        ) {
            stmnt.setString(1, username);
            stmnt.setString(2, hashedPass);
            stmnt.executeUpdate();
        } catch (NullPointerException e) {
            throw new RuntimeException("Invalid connection string...");
        } catch (Exception e) {
            throw new RuntimeException("Could not access database...");
        }
    }

    @Override
    public void successfulLogin() {
        this.loginSuccess = true;
    }

    @Override
    public void successfulRegistration() {
        this.registerSuccess = true;
    }
}
