package by.epam.shapes.entity;

import java.util.Arrays;

public class Shape {
    private int id;
    private String name;
    private Point[] points;

    public Shape() {
    }

    public Shape(int id, String name, Point[] points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return new Shape(id, name, points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return (name.equals(shape.name)) && (id == shape.id) && (points == shape.points);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(points);
        return result;
    }
}
