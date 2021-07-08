package by.epam.shapes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Shape implements Cloneable {

    private static int globalId = 0;

    private int id;
    private List<Point> points;

    public Shape() {
        points = new ArrayList<>();
        this.id = ++globalId;
    }

    public Shape(List<Point> points) {
        this();
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return new Shape(points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return (id == shape.id) && (points.equals(shape.points));
    }

    @Override
    public int hashCode() {
        return 31 * id + (points != null ? points.hashCode() : 0);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Shape.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("points=" + points)
                .toString();
    }
}
