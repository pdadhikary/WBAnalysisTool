package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.exceptions.IncorrectCredentialsException;
import ca.yorku.eecs3311.team09.exceptions.UsernameTakenException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public abstract class UserModel implements IUserModel {
    protected String username;
    protected List<ILoginObserver> loginObservers = new ArrayList<>();
    protected List<IRegistrationObserver> registrationObservers = new ArrayList<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void addLoginObserver(ILoginObserver observer) {
        this.loginObservers.add(observer);
    }

    @Override
    public void notifyLoginObservers() {
        for (ILoginObserver observer : this.loginObservers) {
            observer.successfulLogin();
        }
    }

    @Override
    public void addRegistrationObserver(IRegistrationObserver observer) {
        this.registrationObservers.add(observer);
    }

    @Override
    public void notifyRegistrationObservers() {
        for (IRegistrationObserver observer : this.registrationObservers) {
            observer.successfulRegistration();
        }
    }

    @Override
    public abstract void registerUser(String username, String password) throws UsernameTakenException;

    @Override
    public abstract void loginUser(String username, String password) throws IncorrectCredentialsException;

    /**
     * Checks whether the plain text password matches the hashed password.
     *
     * @param plainTextPassword plain text password to check
     * @param hashedPassword    hashed password to check against
     * @return true if the plainTextPassword matches hashedPassword, false otherwise.
     */
    protected boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /**
     * Hash a password using the OpenBSD bcrypt scheme.
     *
     * @param plainTextPassword plain text password
     * @return the hashed password.
     */
    protected String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
