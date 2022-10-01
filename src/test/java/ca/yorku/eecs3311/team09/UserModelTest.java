package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.models.UserModel;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.Assert.assertTrue;

public class UserModelTest {
    @Test
    public void testConstructorHashing() {
        // test hashing in constructor

        String user = "user01", pass = "123";
        UserModel u = new UserModel(user, pass);

        assert !pass.equals(u.getHashedPassword());
    }

    @Test
    public void testPasswordMatching() {
        // test password matching

        String user1 = "user01", pass = "123";
        UserModel u = new UserModel(user1, pass);

        assertTrue(u.checkPassword(pass));
    }

    @Test
    public void testHashingMethod() {
        // test hashing in method

        String user = "user01", pass = "123";
        UserModel u = new UserModel(user);
        u.setHashedPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
        assertTrue(u.checkPassword(pass));
    }
}
