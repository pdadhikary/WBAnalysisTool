package ca.yorku.eecs3311.team09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.controller.LoginController;
import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IRegistrationObserver;
import ca.yorku.eecs3311.team09.models.IUserModel;

public class LoginControllerTest {
	protected ILoginController controller;
	public static boolean registrationMade;
	public static boolean submissionMade;

	@Before
	public void before() {
		controller = new LoginController(new DummyUserModel());
		LoginControllerTest.registrationMade = false;
		LoginControllerTest.submissionMade = false;
	}

	/**
	 * Test ID: LoginControllerValidationTest01
	 * <p>
	 * Category: Tests the empty username validation checker of the LoginController.
	 * <p>
	 * Requirement Coverage: UC1-Registration-Empty-Username-Validation.
	 * <p>
	 * Initial Condition: username is empty.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Initialize a ILoginController.
	 * <p>
	 * - 2. Call the register() method of the ILoginController with an empty
	 * username.
	 * <p>
	 * Expected Outcome: Throws a ValidationException with a useful message.
	 */
	@Test(expected = ValidationException.class)
	public void LoginControllerValidationTest01() {
		String username = "";
		String password = "pass1";

		controller.register(username, password);
	}

	/**
	 * Test ID: LoginControllerValidationTest02
	 * <p>
	 * Category: Tests the spaced username validation checker of the
	 * LoginController.
	 * <p>
	 * Requirement Coverage: UC1-Registration-Spaced-Username-Validation
	 * <p>
	 * Initial Condition: username contains spaces.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Initialize a ILoginController.
	 * <p>
	 * - 2. Call the register() method of the ILoginController with a username
	 * containing spaces.
	 * <p>
	 * Expected Outcome: Throws a ValidationException with a useful message.
	 */
	@Test(expected = ValidationException.class)
	public void LoginControllerValidationTest02() {
		String username = "    s";
		String password = "pass1";

		controller.register(username, password);
	}

	/**
	 * Test ID: LoginControllerValidationTest03
	 * <p>
	 * Category: Tests the empty password validation checker of the LoginController.
	 * <p>
	 * Requirement Coverage: UC1-Registration-Empty-Password-Validation
	 * <p>
	 * Initial Condition: Password is empty.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Initialize a ILoginController.
	 * <p>
	 * - 2. Call the register() method of the ILoginController with an empty
	 * password.
	 * <p>
	 * Expected Outcome: Throws a ValidationException with a useful message.
	 */
	@Test(expected = ValidationException.class)
	public void LoginControllerValidationTest03() {
		String username = "user1";

		String password = "";

		controller.register(username, password);
	}

	@Test
	public void LoginControllerValidationTest04() {
		String username = "user1";

		String password = "password";

		controller.register(username, password);

		Assert.assertTrue("Registration was not made", LoginControllerTest.registrationMade);
	}

	@Test
	public void LoginControllerValidationTest05() {
		String username = "user1";

		String password = "password";

		controller.submit(username, password);
		Assert.assertTrue("Submission was not made", LoginControllerTest.submissionMade);
	}

	@Test(expected = ValidationException.class)
	public void LoginControllerValidationTest06() {
		String username = null;

		String password = "";

		controller.register(username, password);
	}

	@Test(expected = ValidationException.class)
	public void LoginControllerValidationTest07() {
		String username = "user1";

		String password = null;

		controller.register(username, password);
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
			LoginControllerTest.registrationMade = true;
		}

		@Override
		public void loginUser(String username, String password) throws IncorrectCredentialsException {
			LoginControllerTest.submissionMade = true;
		}
	}
}
