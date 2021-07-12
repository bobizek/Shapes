package by.epam.shapes.dao.creator;

import by.epam.shapes.dao.parser.impl.PointsParser;
import by.epam.shapes.dao.reader.impl.PointReader;
import by.epam.shapes.dao.validator.impl.TetrahedronSourceValidator;
import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.entity.Tetrahedron;
import by.epam.shapes.helper.exception.ProjectException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ShapeCreatorTest {
    @Test
    void createShape() throws ProjectException {
        TetrahedronSourceValidator validator = new TetrahedronSourceValidator();
        PointsParser parser = new PointsParser();
        PointReader reader = new PointReader("src//main//resources//textfiles//file.txt", validator);
        ShapeCreator creator = new ShapeCreator(reader, parser);
        List<Shape> shapes = creator.createShapes();
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(-1, -1, 1);
        Point p3 = new Point(-1, 1, -1);
        Point p4 = new Point(1, -1, -1);
        List<Point> pointList = new ArrayList<>();
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        //(1, 1, 1) (-1, -1, 1) (-1, 1, -1) (1, -1, -1)
        //(-1, 1, 1) (1, -1, 1) (1, 1, -1) (-1, -1, -1)
        Tetrahedron tetrahedron = new Tetrahedron(pointList);
        System.out.println(shapes.get(0));
        System.out.println(shapes.get(1));
        System.out.println(tetrahedron);
        Assert.assertTrue((tetrahedron.getPoints()).equals(shapes.get(0).getPoints()));
    }
}