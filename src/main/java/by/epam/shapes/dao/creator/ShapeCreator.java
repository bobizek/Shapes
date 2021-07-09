package by.epam.shapes.dao.creator;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.exception.ReaderException;
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

    public List<Shape> createShapes() throws ReaderException { // TODO: обернуть ридерэкзепшн в другой кастомный и бросить
        List<Shape> shapes = new ArrayList<>();
        List<List<Point>> parsedPoints = parser.parsePoints(reader.readPoints());
        for (List<Point> points : parsedPoints) {
            shapes.add(createShape(points));
        }
        return shapes;
    }

    public Shape createShape(List<Point> points) {
        return BaseShapeService.of(ShapeType.TETRAHEDRON).isRegularTetrahedron(points) ?
                new RegularTetrahedron(points) :
                new Shape(points);
    }
}

