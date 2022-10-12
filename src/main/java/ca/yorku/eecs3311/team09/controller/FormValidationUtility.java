package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.exceptions.ValidationException;

public class FormValidationUtility {
    private FormValidationUtility() {

    }

    public static void checkNotNull(String str, String fieldName) {
        if (str == null) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }

    public static void checkNotEmpty(String str, String fieldName) {
        if (str.length() < 1) {
            throw new ValidationException(fieldName + " cannot be empty!");
        }
    }

    public static void notContainSpace(String str, String fieldName) {
        if (str.indexOf(' ') != -1) {
            throw new ValidationException(fieldName + " cannot contain spaces!");
        }
    }
}
