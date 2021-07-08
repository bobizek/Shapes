package by.epam.shapes.creator;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.exception.ReaderException;
import by.epam.shapes.parser.Parser;
import by.epam.shapes.reader.Reader;
import by.epam.shapes.util.ShapeService;

import java.util.List;

public class ShapeCreator {

    private final Parser parser;
    private final Reader reader;
    //private final ShapeRepository shapeRepository;
    //private final TetrahedronRepository tetrahedronRepository;

    public ShapeCreator(Reader reader, Parser  parser/*, ShapeRepository shapeRepository, TetrahedronRepository tetrahedronRepository*/) {
        this.reader = reader;
        this.parser = parser;
        //this.shapeRepository = shapeRepository;
        //this.tetrahedronRepository = tetrahedronRepository;
    }

    public Shape createShape(List<Point> points) {
        return ShapeService.isRegularTetrahedron(points) ?
                new RegularTetrahedron(points, ShapeService.findVertex(points)) :
                new Shape(points);
    }
}

