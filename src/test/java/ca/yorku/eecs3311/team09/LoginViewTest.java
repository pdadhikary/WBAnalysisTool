package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;
import ca.yorku.eecs3311.team09.views.LoginView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

public class LoginViewTest {
    private static class DummyLoginController implements ILoginController {
        @Override
        public void register(String username, String password) throws ValidationException, UsernameTakenException {
            LoginViewTest.username = username;
            LoginViewTest.password = password;
        }

        @Override
        public void submit(String username, String password) throws IncorrectCredentialsException {
            LoginViewTest.username = username;
            LoginViewTest.password = password;
        }

        @Override
        public void showLoginView() {

        }

        @Override
        public void disposeLoginView() {

        }
    }

    private static class TestLoginView extends LoginView {
        /**
         * Returns a new LoginView
         *
         * @param controller the controller of this view
         */
        public TestLoginView(ILoginController controller) {
            super(controller);
        }
    }

    protected static String username;
    protected static String password;

    @Before
    public void before() {
        LoginViewTest.username = "";
        LoginViewTest.password = "";
    }

    @Test
    public void LoginViewTest01() {
        ILoginController controller = new DummyLoginController();
        LoginView view = new TestLoginView(controller);

        String username = "jeffd124";
        String password = "password";

        view.getUsernameField().setText(username);
        view.getPasswordField().setText(password);

        view.actionPerformed(new ActionEvent(view.getSubmitBtn(), 0, "submit"));

        Assert.assertEquals("Incorrect username was passed to controller.", username, LoginViewTest.username);
        Assert.assertEquals("Incorrect password was passed to controller.", password, LoginViewTest.password);
    }

    @Test
    public void LoginViewTest02() {
        ILoginController controller = new DummyLoginController();
        LoginView view = new TestLoginView(controller);

        String username = "kellyk1989";
        String password = "123456";

        view.getUsernameField().setText(username);
        view.getPasswordField().setText(password);

        view.actionPerformed(new ActionEvent(view.getRegisterBtn(), 1, "register"));

        Assert.assertEquals("Incorrect username was passed to controller.", username, LoginViewTest.username);
        Assert.assertEquals("Incorrect password was passed to controller.", password, LoginViewTest.password);
    }
}
