package by.epam.shapes.util;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class TetrahedronService extends BaseShapeService<Tetrahedron> {

    private static TetrahedronService instance;

    public static TetrahedronService getInstance() {
        return instance != null ? instance : (instance = new TetrahedronService());
    }

    private TetrahedronService() { }

    @Override
    public double countArea(Tetrahedron tetrahedron) {
        Point vertex = findVertex(tetrahedron.getPoints());
        Point p1 = findBasePoints(tetrahedron.getPoints()).get(0);
        Point p2 = findBasePoints(tetrahedron.getPoints()).get(1);
        Point p3 = findBasePoints(tetrahedron.getPoints()).get(2);

        return findEquilateralTriangleArea(p1, p2, p3) +
                findEquilateralTriangleArea(vertex, p1, p2) +
                findEquilateralTriangleArea(vertex, p2, p3) +
                findEquilateralTriangleArea(vertex, p3, p1);
    }

    @Override
    public double countVolume(Tetrahedron tetrahedron) {
        Point vertex = findVertex(tetrahedron.getPoints());
        Point p1 = findBasePoints(tetrahedron.getPoints()).get(0);
        Point p2 = findBasePoints(tetrahedron.getPoints()).get(1);
        Point p3 = findBasePoints(tetrahedron.getPoints()).get(2);

        return Math.sqrt(Math.pow(findPointsDistance(vertex, p1), 2) -
                Math.pow((findEquilateralTriangleHeight(p1, p2, p3)) / 2 , 2)) *
                findEquilateralTriangleArea(p1, p2, p3) / 3;
    }

    @Override
    public float findVolumeRatiosDissectedByPlane(List<Point> plane) {
        return 0.5f;
    }

    @Override
    public boolean baseIsOnCoordinatePlane(Tetrahedron tetrahedron) {
        return baseIsOnCoordinatePlane(tetrahedron, List.of(Point::getX, Point::getY, Point::getZ));
    }

    @Override
    public Point findVertex(List<Point> points) {
        return findVertex(points, new ArrayList<>(Arrays.asList(Point::getX, Point::getY, Point::getZ)));
    }

    private boolean baseIsOnCoordinatePlane(Tetrahedron tetrahedron, List<Function<Point, Integer>> gettersChain) {
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

    private double findEquilateralTriangleArea(Point p1, Point p2, Point p3) {
        return findEquilateralTriangleHeight(p1, p2, p3) * findPointsDistance(p2, p3) / 2;
    }

    private double findEquilateralTriangleHeight(Point p1, Point p2, Point p3) {
        return Math.sqrt(Math.pow(findPointsDistance(p1, p2), 2) - Math.pow(findPointsDistance(p2, p3) / 2, 2));
    }

}
