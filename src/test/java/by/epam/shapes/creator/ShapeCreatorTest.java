package by.epam.shapes.creator;

import by.epam.shapes.exception.ReaderException;
import by.epam.shapes.parser.impl.PointsParser;
import by.epam.shapes.reader.impl.PointReader;
import by.epam.shapes.repository.ShapeRepository;
import by.epam.shapes.repository.TetrahedronRepository;
import by.epam.shapes.validator.impl.TetrahedronSourceValidator;
import org.junit.jupiter.api.Test;

class ShapeCreatorTest {
    @Test
    void createShape() throws ReaderException {
        TetrahedronSourceValidator validator = new TetrahedronSourceValidator();
        PointsParser parser = new PointsParser();
        PointReader reader = new PointReader("src//main//resources//textfiles//file.txt", validator);
        ShapeRepository shapeRepository = new ShapeRepository();
        TetrahedronRepository tetrahedronRepository = new TetrahedronRepository();
        ShapeCreator creator = new ShapeCreator(reader, parser);
    }
}