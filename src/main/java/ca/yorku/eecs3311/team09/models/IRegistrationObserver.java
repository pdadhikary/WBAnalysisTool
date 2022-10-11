package ca.yorku.eecs3311.team09.models;

/**
 * An observer of a successful {@link UserModel User} registration
 */
public interface IRegistrationObserver {
    /**
     * performs action upon a successful registration.
     */
    void successfulRegistration();
}
