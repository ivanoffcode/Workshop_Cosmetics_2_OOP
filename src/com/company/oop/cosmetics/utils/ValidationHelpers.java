package com.company.oop.cosmetics.utils;

import java.util.List;

public class ValidationHelpers {

    private static final String STRING_LENGTH_ERROR = "%s should be between %d and %d symbols.";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";
    private static final int NEGATIVE_NUMBER = 0;
    private static final String NEGATIVE_INT_ERROR = "%s should be non negative.";

    public static void validateIntRange(int minLength, int maxLength, int actualLength, String type) {
        if (actualLength < minLength || actualLength > maxLength) {
            throw new IllegalArgumentException(String.format(STRING_LENGTH_ERROR, type, minLength, maxLength));
        }
    }

    public static void validatePositiveDouble(double value, String type) {
        if (value < NEGATIVE_NUMBER) {
            throw new IllegalArgumentException(String.format(NEGATIVE_INT_ERROR, type));
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String type) {
        validateIntRange(minLength, maxLength, stringToValidate.length(), type);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters || list.size() > expectedNumberOfParameters) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS,
                    expectedNumberOfParameters, list.size()));
        }
    }


}
