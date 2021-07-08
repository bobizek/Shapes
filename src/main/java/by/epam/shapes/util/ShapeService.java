package by.epam.shapes.util;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.entity.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class ShapeService {

    private static List<Point> findBasePoints(List<Point> points) {
        List<Point> basePoints = new ArrayList<>(); // Point[(points.size()) - 1];
        Point vertex = findVertex(points);
        for (Point point : points) {
            if(!point.equals(vertex)) {
                basePoints.add(point);
            }
        }
        return basePoints;
    }

    public static double findPointsDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                         Math.pow(point2.getY() - point1.getY(), 2) +
                         Math.pow(point2.getZ() - point1.getZ(), 2));
    }

    public static double findEquilateralTriangleArea(Point p1, Point p2, Point p3) {
        return findEquilateralTriangleHeight(p1, p2, p3) * findPointsDistance(p2, p3) / 2;
    }

    public static double findEquilateralTriangleHeight(Point p1, Point p2, Point p3) {
        return Math.sqrt(Math.pow(findPointsDistance(p1, p2), 2) - Math.pow(findPointsDistance(p2, p3) / 2, 2));
    }

    public static double countArea(RegularTetrahedron tetrahedron) {
        Point vertex = findVertex(tetrahedron.getPoints());
        Point p1 = findBasePoints(tetrahedron.getPoints()).get(0);
        Point p2 = findBasePoints(tetrahedron.getPoints()).get(1);
        Point p3 = findBasePoints(tetrahedron.getPoints()).get(2);

        return findEquilateralTriangleArea(p1, p2, p3) +
                findEquilateralTriangleArea(vertex, p1, p2) +
                findEquilateralTriangleArea(vertex, p2, p3) +
                findEquilateralTriangleArea(vertex, p3, p1);
    }

    public static double countVolume(RegularTetrahedron tetrahedron) {
        Point vertex = findVertex(tetrahedron.getPoints());
        Point p1 = findBasePoints(tetrahedron.getPoints()).get(0);
        Point p2 = findBasePoints(tetrahedron.getPoints()).get(1);
        Point p3 = findBasePoints(tetrahedron.getPoints()).get(2);

        return Math.sqrt(Math.pow(findPointsDistance(vertex, p1), 2) -
                Math.pow((findEquilateralTriangleHeight(p1, p2, p3)) / 2 , 2)) *
                findEquilateralTriangleArea(p1, p2, p3) / 3;
    }

    public static boolean isRegularTetrahedron(List<Point> points) {
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

    public static String findVolumeRatiosDissectedByPlane(int[] plane) {
        return "a";
    }

    public static boolean baseIsOnCoordinatePlane(RegularTetrahedron tetrahedron) {
        return baseIsOnCoordinatePlane(tetrahedron, List.of(Point::getX, Point::getY, Point::getZ));
    }

    private static boolean baseIsOnCoordinatePlane(RegularTetrahedron tetrahedron, List<Function<Point, Integer>> gettersChain) {
        if(gettersChain.isEmpty()) {
            return false;
        }
        Function<Point, Integer> currentGetter = gettersChain.get(gettersChain.size() - 1);
        for (int i = 0; i < tetrahedron.getPoints().size(); ++i) {
            Point currentPoint = tetrahedron.getPoints().get(i);
            if (currentGetter.apply(currentPoint) !=0) {
                gettersChain.remove(currentGetter);
                return baseIsOnCoordinatePlane(tetrahedron, gettersChain);
            }
        }
        return true;
    }

    public static boolean basePointsInTheSamePlane(List<Point> points) {
        return findVertex(points, List.of(Point::getX, Point::getY, Point::getZ)) != null;
    }

    public static Point findVertex(List<Point> points) {
        return findVertex(points, List.of(Point::getX, Point::getY, Point::getZ));
    }

    private static Point findVertex(List<Point> points, List<Function<Point, Integer>> gettersChain) {
        if(gettersChain.isEmpty()) {
            return null;
        }
        int equals = 0;
        Point vertex = null;
        Function<Point, Integer> currentGetter = gettersChain.get(gettersChain.size() - 1);
        for (int i = 0; i < points.size(); ++i) {
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

}
