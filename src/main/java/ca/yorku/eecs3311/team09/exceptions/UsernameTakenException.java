package ca.yorku.eecs3311.team09.exceptions;

/**
 * An exception that signals that a username is already being used.
 */
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String msg) {
        super(msg);
    }
}
