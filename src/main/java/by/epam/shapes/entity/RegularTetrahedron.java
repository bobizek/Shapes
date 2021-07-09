package by.epam.shapes.entity;

import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.util.BaseShapeService;

import java.util.List;
import java.util.StringJoiner;

public class RegularTetrahedron extends Shape {

    private Point topPoint;

    public RegularTetrahedron() {
        super();
    }

    public RegularTetrahedron(List<Point> points) {
        super(points);
        setTopPoint(BaseShapeService.of(ShapeType.TETRAHEDRON).findVertex(points));
    }

    public Point getTopPoint() {
        return topPoint.clone();
    }

    private void setTopPoint(Point topPoint) {
        this.topPoint = topPoint.clone();
    }

    @Override
    public void setPoints(List<Point> points) {
        if(BaseShapeService.of(ShapeType.TETRAHEDRON).isRegularTetrahedron(points)) {
            super.setPoints(points);
            setTopPoint(BaseShapeService.of(ShapeType.TETRAHEDRON).findVertex(points));
        }
    }

    @Override
    public RegularTetrahedron clone() {
        return new RegularTetrahedron(getPoints());
    }

    @Override
    public boolean equals(Object o) { // TODO: 8.07.21
        RegularTetrahedron that = (RegularTetrahedron) o;
        return super.equals(o) && topPoint != null ? topPoint.equals(that.topPoint) : that.topPoint == null;
    }

    @Override
    public int hashCode() { // TODO: 8.07.21
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
