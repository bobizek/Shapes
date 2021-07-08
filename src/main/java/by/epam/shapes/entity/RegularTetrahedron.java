package by.epam.shapes.entity;

import java.util.List;
import java.util.StringJoiner;

public class RegularTetrahedron extends Shape {

    private Point topPoint;

    public RegularTetrahedron() {
        super();
    }

    public RegularTetrahedron(List<Point> points, Point topPoint) {
        super(points);
        this.topPoint = topPoint;
    }

    public Point getTopPoint() {
        return topPoint;
    }

    public void setTopPoint(Point topPoint) {
        this.topPoint = topPoint;
    }

    @Override
    public RegularTetrahedron clone() throws CloneNotSupportedException {
        return new RegularTetrahedron(getPoints(), getTopPoint());
    }

    @Override
    public boolean equals(Object o) { // TODO: 8.07.21
        super.equals(o);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RegularTetrahedron that = (RegularTetrahedron) o;

        return topPoint != null ? topPoint.equals(that.topPoint) : that.topPoint == null;
    }

    @Override
    public int hashCode() { // TODO: 8.07.21
        super.hashCode();
        int result = super.hashCode();
        result = 31 * result + (topPoint != null ? topPoint.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() { // TODO: 8.07.21
        return new StringJoiner(", ", RegularTetrahedron.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("topPoint=" + topPoint)
                .toString();
    }


}
