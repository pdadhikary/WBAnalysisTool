package ca.yorku.eecs3311.team09.models;

/**
 * An observer of a successful login attempt from a {@link UserModel User}
 */
public interface ILoginObserver {
    /**
     * performs action upon a successful login.
     */
    void successfulLogin();
}
