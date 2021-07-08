package by.epam.shapes.reader.impl;

import by.epam.shapes.exception.ReaderException;
import by.epam.shapes.reader.Reader;
import by.epam.shapes.validator.ShapeSourceValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PointReader implements Reader {

    private final String filepath;
    private final ShapeSourceValidator sourceValidator;

    public PointReader(String filepath, ShapeSourceValidator sourceValidator) {
        this.sourceValidator = sourceValidator;
        this.filepath = filepath;
    }

    public String readPoints() throws ReaderException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (sourceValidator.isValid(line)) {
                    result.append(line);
                }
            }
            return result.toString();
        } catch (IOException e) {
            throw new ReaderException("Cannot read array: ", e);
        }
    }

}
