package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.RestrictedDataException;
import ca.yorku.eecs3311.team09.exceptions.ValidationException;

import java.util.List;

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

    /**
     * Throws a {@link ValidationException} if the value is less than value floor.
     *
     * @param min   value floor
     * @param value value
     * @param msg   message
     */
    public static void checkLessThanEqual(Integer min, Integer value, String msg) {
        if (value < min)
            throw new ValidationException(msg);
    }

    /**
     * Throws a {@link ValidationException} if a value in the list of inputs is within the range of [min, max].
     *
     * @param min    value floor
     * @param max    value ceiling
     * @param inputs inputs
     * @param msg    message
     */
    public static void restrictInputs(Integer min, Integer max, List<Integer> inputs, String msg) {
        for (Integer input : inputs) {
            if (input <= max && input >= min)
                throw new RestrictedDataException(msg);
        }
    }

    /**
     * Throws a {@link ValidationException} if value is contained within the list of restrictions.
     *
     * @param value        value
     * @param restrictions restrictions
     * @param msg          message
     */
    public static void restrictInputs(String value, List<String> restrictions, String msg) {
        for (String input : restrictions) {
            if (input.equals(value))
                throw new RestrictedDataException(msg);
        }
    }
}
