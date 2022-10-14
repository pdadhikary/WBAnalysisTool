package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.controller.LoginController;
import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IRegistrationObserver;
import ca.yorku.eecs3311.team09.models.IUserModel;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class LoginControllerValidationTest {
    protected ILoginController controller;

    @Before
    public void before() {
        controller = new LoginController(new DummyUserModel());
    }

    /**
     * @testId LoginControllerValidationTest01
     * @category Tests the empty username validation checker of the LoginController.
     * @testCoverage UC1-Registration-Empty-Username-Validation
     * @precondition username is empty.
     * @procedure Steps required for this test:
     * 1. Initialize a ILoginController.
     * 1. Call the register() method of the ILoginController with an empty username.
     * @expectedOutcome Throws a ValidationException with a useful message.
     */
    @Test
    public void LoginControllerValidationTest01() throws SQLException {
        String password = "pass1";

        String expectedMessage = "username cannot be empty!";

        try {
            controller.register("", password);
        } catch (ValidationException e) {
            assertEquals("Did not receive the expected message...", expectedMessage, e.getMessage());
            return;
        }

        throw new RuntimeException();
    }

    /**
     * @testId LoginControllerValidationTest02
     * @category Tests the spaced username validation checker of the LoginController.
     * @testCoverage UC1-Registration-Spaced-Username-Validation
     * @precondition username contains spaces.
     * @procedure Steps required for this test:
     * 1. Initialize a ILoginController.
     * 1. Call the register() method of the ILoginController with a username containing spaces.
     * @expectedOutcome Throws a ValidationException with a useful message.
     */
    @Test
    public void LoginControllerValidationTest02() throws SQLException {
        String password = "pass1";

        String expectedMessage = "username cannot contain spaces!";

        try {
            controller.register("   s", password);
        } catch (ValidationException e) {
            assertEquals("Did not receive the expected message...", expectedMessage, e.getMessage());
            return;
        }

        throw new RuntimeException();
    }

    /**
     * @testId LoginControllerValidationTest03
     * @category Tests the empty password validation checker of the LoginController.
     * @testCoverage UC1-Registration-Empty-Password-Validation
     * @precondition Password is empty.
     * @procedure Steps required for this test:
     * 1. Initialize a ILoginController.
     * 1. Call the register() method of the ILoginController with an empty password.
     * @expectedOutcome Throws a ValidationException with a useful message.
     */
    @Test
    public void LoginControllerValidationTest03() throws SQLException {
        String username = "user1";

        String expectedMessage = "password cannot be empty!";

        try {
            controller.register(username, "");
        } catch (ValidationException e) {
            assertEquals("Did not receive the expected message...", expectedMessage, e.getMessage());
            return;
        }

        throw new RuntimeException();
    }

    private static class DummyUserModel implements IUserModel {

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public void addLoginObserver(ILoginObserver observer) {

        }

        @Override
        public void notifyLoginObservers() {

        }

        @Override
        public void addRegistrationObserver(IRegistrationObserver observer) {

        }

        @Override
        public void notifyRegistrationObservers() {

        }

        @Override
        public void registerUser(String username, String password) throws UsernameTakenException {

        }

        @Override
        public void loginUser(String username, String password) throws IncorrectCredentialsException {

        }
    }
}
