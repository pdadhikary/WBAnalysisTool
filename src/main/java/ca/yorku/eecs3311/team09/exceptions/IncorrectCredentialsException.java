package ca.yorku.eecs3311.team09.exceptions;

/**
 * An exception that signals that the username and password are invalid.
 */
public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException(String msg) {
        super(msg);
    }
}
