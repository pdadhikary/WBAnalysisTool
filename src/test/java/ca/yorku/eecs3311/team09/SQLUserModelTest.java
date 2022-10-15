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
     * Test ID: DBContextTest01
     * <p>
     * Category: Tests DBContext connection.
     * <p>
     * Requirement Coverage: UC1-Database-DBContext
     * <p>
     * Initial Condition: user_test.db is present in src/main/resources/database/test/
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a test database (automated).
     * <p>
     * - 2. Set DBContext connection string to point to the test database.
     * <p>
     * - 3. Call getConnection() method on the DBContext instance.
     * <p>
     * Expected Outcome: returns connection to the test database.
     *
     * @throws SQLException if database connection could not be made
     */
    @Test
    public void DBContextTest01() throws SQLException {
        Connection conn = DBContext.getInstance().getConnection();

        assertNotNull("Could not connect to the database", conn);
    }

    /**
     * Test ID: SQLUserModelTest01
     * <p>
     * Category: Tests user registration into database.
     * <p>
     * Requirement Coverage: UC1-User-Registration-SQLUserModel
     * <p>
     * Initial Condition: user_test.db is present in src/main/resources/database/test/ and USER table is present
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a test database (automated).
     * <p>
     * - 2. Create USER table if not present (automated).
     * <p>
     * - 3. Initialize SQLUserModel with a IDBContext instance.
     * <p>
     * - 4. Set username and password.
     * <p>
     * - 5. Call registerUser() method on the UserModel.
     * <p>
     * Expected Outcome: the SuccessfulRegistrationObservers get notified and the user is registered.
     */
    @Test
    public void SQLUserModelTest01() {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        // Become a registration observer
        model.addRegistrationObserver(this);

        model.registerUser(username, password);

        assertTrue("User was not registered!", this.registerSuccess);
    }

    /**
     * Test ID: SQLUserModelTest02
     * <p>
     * Category: Tests user registration error into database.
     * <p>
     * Requirement Coverage: UC1-User-Registration-Error-SQLUserModel
     * <p>
     * Initial Condition: The user already exists in the database.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a test database (automated).
     * <p>
     * - 2. Create USER table if not present (automated).
     * <p>
     * - 3. Initialize SQLUserModel with a IDBContext instance.
     * <p>
     * - 4. Insert the user into the database.
     * <p>
     * - 5. Try and register the user again in the database.
     * <p>
     * Expected Outcome: throws an UsernameTakenException.
     */
    @Test(expected = UsernameTakenException.class)
    public void SQLUserModelTest02() {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        insertUser(username, password);

        model.registerUser(username, "RandomOtherPassword");
    }

    /**
     * Test ID: SQLUserModelTest03
     * <p>
     * Category: Tests user login.
     * <p>
     * Requirement Coverage: UC1-User-Login-SQLUserModel
     * <p>
     * Initial Condition: The user already exists in the database.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a test database (automated).
     * <p>
     * - 2. Create USER table if not present (automated).
     * <p>
     * - 3. Initialize SQLUserModel with a IDBContext instance.
     * <p>
     * - 4. Insert the user into the database.
     * <p>
     * - 5. Try and login the user again.
     * <p>
     * Expected Outcome: the SuccessfulLoginObservers get notified and the user is logged in.
     */
    @Test
    public void SQLUserModelTest03() {
        String username = "user1";
        String password = "userpass";

        IUserModel model = new SQLUserModel(DBContext.getInstance());

        model.addLoginObserver(this);

        insertUser(username, password);

        model.loginUser(username, password);

        assertTrue("User was not logged in!", this.loginSuccess);
    }

    /**
     * Test ID: SQLUserModelTest04
     * <p>
     * Category: Tests user login error.
     * <p>
     * Requirement Coverage: UC1-User-Login-Error-SQLUserModel
     * <p>
     * Initial Condition: The user does not exist or the password does not match.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a test database (automated).
     * <p>
     * - 2. Create USER table if not present (automated).
     * <p>
     * - 3. Initialize SQLUserModel with a IDBContext instance.
     * <p>
     * - 4. Insert the user into the database.
     * <p>
     * - 5. Try and login the user with incorrect username/password.
     * <p>
     * Expected Outcome: throws an IncorrectCredentialsException.
     */
    @Test(expected = IncorrectCredentialsException.class)
    public void SQLUserModelTest04() {
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
