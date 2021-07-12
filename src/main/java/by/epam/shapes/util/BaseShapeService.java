package by.epam.shapes.util;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.helper.exception.ProjectException;
import by.epam.shapes.helper.exception.UnmanagedProjectException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class BaseShapeService<T extends Shape> implements ShapeService<T> {

    public static <T> ShapeService<T> of(ShapeType shapeType) {
        return switch (shapeType) {
            case SHAPE -> throw new UnmanagedProjectException("Shape service could not be implemented");
            case TETRAHEDRON -> (ShapeService<T>) TetrahedronService.getInstance();
        };
    }

    @Override
    public boolean isRegularTetrahedron(List<Point> points) {
        Point vertex = findVertex(points);
        Point p1 = findBasePoints(points).get(0);
        Point p2 = findBasePoints(points).get(1);
        Point p3 = findBasePoints(points).get(2);
        return (vertex != null &&
                findPointsDistance(vertex, p1) == findPointsDistance(vertex, p2) &&
                findPointsDistance(vertex, p2) == findPointsDistance(vertex, p3) &&
                findPointsDistance(p1, p2) == findPointsDistance(p2, p3) &&
                findPointsDistance(p2, p3) == findPointsDistance(p3, p1) &&
                basePointsInTheSamePlane(points));
    }

    protected double findPointsDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                Math.pow(point2.getY() - point1.getY(), 2) +
                Math.pow(point2.getZ() - point1.getZ(), 2));
    }

    protected List<Point> findBasePoints(List<Point> points) {
        List<Point> basePoints = new ArrayList<>(); // Point[(points.size()) - 1];
        Point vertex = findVertex(points);
        for (Point point : points) {
            if(!point.equals(vertex)) {
                basePoints.add(point);
            }
        }
        return basePoints;
    }

    protected Point findVertex(List<Point> points, List<Function<Point, Integer>> gettersChain) {
        if(gettersChain.isEmpty()) {
            return null;
        }
        Point vertex = null;
        Function<Point, Integer> currentGetter = gettersChain.get(gettersChain.size() - 1);
        for (int i = 0; i < points.size() - 2; ++i) {
            Point currentPoint = points.get(i);
            Point nextPoint = points.get(i+1);
            if (!currentGetter.apply(currentPoint).equals(currentGetter.apply(nextPoint))) {
                vertex = currentPoint;

                Point doubleNextPoint = points.get(i+2);
                if(!currentGetter.apply(nextPoint).equals(currentGetter.apply(doubleNextPoint))) {
                    vertex = nextPoint;
                    if(!currentGetter.apply(currentPoint).equals(currentGetter.apply(doubleNextPoint))) {
                        gettersChain.remove(currentGetter);
                        return findVertex(points, gettersChain);
                    }
                }
            }
        }
        return vertex;
    }

    protected boolean basePointsInTheSamePlane(List<Point> points) {
        return findVertex(points, new ArrayList<>(Arrays.asList(Point::getX, Point::getY, Point::getZ))) != null;
    }

}
