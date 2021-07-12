package by.epam.shapes.dao.creator;

import by.epam.shapes.dao.parser.impl.PointsParser;
import by.epam.shapes.dao.reader.impl.PointReader;
import by.epam.shapes.dao.validator.impl.TetrahedronSourceValidator;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.exception.ProjectException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class ShapeCreatorTest {
    @Test
    void createShape() throws ProjectException {
        TetrahedronSourceValidator validator = new TetrahedronSourceValidator();
        PointsParser parser = new PointsParser();
        PointReader reader = new PointReader("src//main//resources//textfiles//file.txt", validator);
        ShapeCreator creator = new ShapeCreator(reader, parser);
        List<Shape> shapes = creator.createShapes();
        Assert.assertTrue();
    }
}