package by.epam.shapes.validator.impl;

import by.epam.shapes.validator.ShapeSourceValidator;

public class TetrahedronSourceValidator implements ShapeSourceValidator {
    private static final String LINE_MATCHER_REGEX = "(\\d+\\s+)*\\d+";

    public boolean isValid(String line) {
        return line.matches(LINE_MATCHER_REGEX);
    }
}
