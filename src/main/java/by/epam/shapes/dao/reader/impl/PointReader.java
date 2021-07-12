package by.epam.shapes.dao.reader.impl;

import by.epam.shapes.helper.exception.ProjectException;
import by.epam.shapes.dao.reader.Reader;
import by.epam.shapes.dao.validator.ShapeSourceValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointReader implements Reader {

    private final String filepath;
    private final ShapeSourceValidator sourceValidator;

    public PointReader(String filepath, ShapeSourceValidator sourceValidator) {
        this.sourceValidator = sourceValidator;
        this.filepath = filepath;
    }

    public List<String> readPoints() throws ProjectException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            List<String> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (sourceValidator.isValid(line)) {
                    result.add(line);
                }
            }
            return result;
        } catch (IOException e) {
            throw new ProjectException("Cannot read array: ", e);
        }
    }

}
