package com.pioneers.service.utils;

// TODO: Create all unit tests for all methods in that class.
public final class NameHelper {

    private NameHelper() {
        throw new AssertionError("Utility class");
    }

    public static String buildFullName(final String firstName, final String lastName) {
        return firstName.trim() + " " + lastName.trim();
    }
}
