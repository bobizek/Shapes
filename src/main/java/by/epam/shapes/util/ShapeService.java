package by.epam.shapes.util;

import by.epam.shapes.entity.Point;

import java.util.List;

public interface ShapeService<T> {
    double countArea(T shape);
    double countVolume(T shape);
    boolean baseIsOnCoordinatePlane(T shape);
    boolean isRegularTetrahedron(List<Point> points);
    float findVolumeRatiosDissectedByPlane(List<Point> plane);
    Point findVertex(List<Point> points);
}
