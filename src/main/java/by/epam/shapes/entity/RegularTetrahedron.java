package by.epam.shapes.entity;

public class RegularTetrahedron extends Shape {
    private String name;
    private Point[] points;
    private Point topPoint;

    public RegularTetrahedron() {
        super();
    }

    public RegularTetrahedron(String name, Point[] points, Point topPoint) {
        super();
        this.name = name;
        this.points = points;
        this.topPoint = topPoint;
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
}
