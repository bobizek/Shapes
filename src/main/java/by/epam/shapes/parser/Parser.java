package by.epam.shapes.parser;

import by.epam.shapes.entity.Point;

import java.util.List;

public interface Parser {
    List<Point> parsePoints(String line);
}
