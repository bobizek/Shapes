package by.epam.shapes.dao.parser.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.dao.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class PointsParser implements Parser {

    private static final String LINE_SPLIT_REGEX = "\\)\\s+\\(";
    private static final String LINE_FILTER_REGEX = "[\\)\\(\\,]";

    @Override
    public List<List<Point>> parsePoints(List<String> lines) {
        List<List<Point>> totalPoints = new ArrayList<>();
        for (String line : lines) {
            List<Point> points = new ArrayList<>();
            for (String strPoint : line.split(LINE_SPLIT_REGEX)) {
                String[] xyz = strPoint.replaceAll(LINE_FILTER_REGEX, "").split(" ");
                points.add(new Point(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2])));
            }
            totalPoints.add(points);
        }
        return totalPoints;
    }
}
