package ca.yorku.eecs3311.team09.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

public abstract class UserModel implements IUserModel {
    protected String username;
    protected String hashedPassword;
    protected List<ILoginObserver> loginObservers;
    protected List<IRegistrationObserver> registrationObservers;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void setPassword(String password) {
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
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
    public abstract void registerUser();

    @Override
    public abstract void loginUser();

    /**
     * Returns true if Object is a UserModel with the same username as this UserModel, false otherwise.
     *
     * @param o Object to compare
     * @return true if Object is a UserModel with the same username as this UserModel, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SQLUserModel)) return false;
        SQLUserModel SQLUserModel = (SQLUserModel) o;
        return this.username.equals(SQLUserModel.getUsername());
    }

    /**
     * Returns the hash code of this UserModel
     *
     * @return hash code of this UserModel
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Checks whether the provided raw text password belongs this UserModel.
     *
     * @param password rew text password to check.
     * @return true if the password belongs to this UserModel, false otherwise.
     */
    protected boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
    }
}
