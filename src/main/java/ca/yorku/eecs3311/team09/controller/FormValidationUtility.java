package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.ValidationException;

/**
 * Provides utility functions to validate input fields.
 */
public class FormValidationUtility {
    private FormValidationUtility() {
    }

    /**
     * Checks if field is null.
     *
     * @param field     field
     * @param fieldName name of the field
     * @param <E>       type of the field
     * @throws ValidationException if the field is null.
     */
    public static <E> void checkNotNull(E field, String fieldName) throws ValidationException {
        if (field == null) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }

    /**
     * Checks if the string field is an empty string.
     *
     * @param str       string field
     * @param fieldName field name
     * @throws ValidationException if the string field is an empty string.
     */
    public static void checkNotEmpty(String str, String fieldName) throws ValidationException {
        if (str.length() < 1) {
            throw new ValidationException(fieldName + " cannot be empty!");
        }
    }

    /**
     * Checks if the string field contains a space character.
     *
     * @param str       string field
     * @param fieldName field name
     * @throws ValidationException if the string field contains a space character.
     */
    public static void notContainSpace(String str, String fieldName) throws ValidationException {
        if (str.indexOf(' ') != -1) {
            throw new ValidationException(fieldName + " cannot contain spaces!");
        }
    }
}
