package by.epam.shapes.reader.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.exception.PointReaderException;
import by.epam.shapes.reader.Reader;
import by.epam.shapes.validator.ShapeSourceValidator;
import by.epam.shapes.validator.TetrahedronValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PointReaderImpl implements Reader {

    private final String filepath;
    private final ShapeSourceValidator sourceValidator;
    private final TetrahedronValidator tetrahedronValidator;

    public PointReaderImpl(String filepath, TetrahedronValidator tetrahedronValidator, ShapeSourceValidator sourceValidator) {
        this.sourceValidator = sourceValidator;
        this.tetrahedronValidator = tetrahedronValidator;
        this.filepath = filepath;
    }

    public String readArray() throws PointReaderException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            Point[] points = null;
            while ((line = reader.readLine()) != null) {
                if (sourceValidator.isValid(line)) {
                    points = new Point[];
                    return line;
                }
            }
            throw new PointReaderException("Source file doesn't contain any valid lines for parsing integer array");
        } catch (IOException e) {
            throw new PointReaderException("Cannot read array: ", e);
        }
    }

}
