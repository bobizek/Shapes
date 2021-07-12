package by.epam.shapes.dao.creator;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Tetrahedron;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.exception.ProjectException;
import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.dao.parser.Parser;
import by.epam.shapes.dao.reader.Reader;
import by.epam.shapes.util.BaseShapeService;

import java.util.ArrayList;
import java.util.List;

public class ShapeCreator {

    private final Parser parser;
    private final Reader reader;

    public ShapeCreator(Reader reader, Parser  parser) {
        this.reader = reader;
        this.parser = parser;
    }

    public List<Shape> createShapes() { // TODO: fix exception + log
        List<Shape> shapes = new ArrayList<>();
        try {
            List<List<Point>> parsedPoints = parser.parsePoints(reader.readPoints());
            for (List<Point> points : parsedPoints) {
                shapes.add(createShape(points));
            }
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        return shapes;
    }

    public Shape createShape(List<Point> points) {
        return BaseShapeService.of(ShapeType.TETRAHEDRON).isRegularTetrahedron(points) ?
                new Tetrahedron(points) :
                new Shape(points);
    }
}

