package by.epam.shapes.parser.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class PointsParser implements Parser {

    //(1, 2, 3) (1, 4, 5) (1, 2, 4) (5, 6, 3)
    private static final String LINE_SPLIT_REGEX = "\\)\\s+\\(";
    private static final String LINE_FILTER_REGEX = "[\\)\\(\\,]";

    @Override
    public List<Point> parsePoints(String line) {
        List<Point> points = new ArrayList<>();
        for (String strPoint : line.split(LINE_SPLIT_REGEX)) {
            String[] xyz = strPoint.replaceAll(LINE_FILTER_REGEX, "").split(" ");
            points.add(new Point(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2])));
        }
        return points;
    }
}
