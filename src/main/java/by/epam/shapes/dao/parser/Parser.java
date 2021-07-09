package by.epam.shapes.dao.parser;

import by.epam.shapes.entity.Point;

import java.util.List;

public interface Parser {
    List<List<Point>> parsePoints(List<String> lines);
}
