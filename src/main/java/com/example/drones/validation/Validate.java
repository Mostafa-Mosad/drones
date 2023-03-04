package com.example.drones.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validate {

    private static final String NAME = "^[a-zA-Z0-9-_]+$";
    private static final Pattern namePattern = Pattern.compile(NAME);

    private static final String CODE = "^[A-Z-0-9_]+$";
    private static final Pattern codePattern = Pattern.compile(CODE);

    public static void notNull(Object object, String fieldName) {
        if(object == null)
            throw new IllegalArgumentException("The entered '" + fieldName + "' field must not be null!");
    }

    public static void notBlank(String value, String fieldName) {
        notNull(value, fieldName);
        if(value.trim().isEmpty())
            throw new IllegalArgumentException("The entered '" + fieldName + "' field must not be empty!");
    }

    public static void validateSize(String value, int max, String fieldName) {
        notBlank(value, fieldName);
        if(value.trim().length() > max)
            throw new IllegalArgumentException("The entered '" + fieldName + "' field must not be greater than " + max +"!");
    }

    public static void validateSize(Integer value, int max, String fieldName) {
        notNull(value, fieldName);
        if(value > max)
            throw new IllegalArgumentException("The entered '" + fieldName + "' field must not be greater than " + max +"!");
    }

    public static void validateNameContainsLettersNumberDashAndUnderScore(String name, String fieldName) {
        final Matcher matcher = namePattern.matcher(name);
        if(!matcher.matches())
            throw new IllegalArgumentException("The entered '" + fieldName + "' wrong, allowed only letters, numbers, ‘-‘, ‘_’");
    }

    public static void validateNameContainsUpperLettersNumberAndUnderScore(String name, String fieldName) {
        final Matcher matcher = codePattern.matcher(name);
        if(!matcher.matches())
            throw new IllegalArgumentException("The entered '" + fieldName + "' wrong, allowed only upper letters, numbers, ‘_’");
    }
}
