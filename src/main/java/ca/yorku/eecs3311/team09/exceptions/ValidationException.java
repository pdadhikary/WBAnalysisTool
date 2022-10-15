package ca.yorku.eecs3311.team09.exceptions;

/**
 * Exception thrown when a field does not meet a validation criteria.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
