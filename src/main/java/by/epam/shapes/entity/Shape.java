package by.epam.shapes.entity;

import by.epam.shapes.helper.event.Event;
import by.epam.shapes.helper.event.impl.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class Shape implements Cloneable {

    private static int globalId = 0;

    private final int id;
    private final List<Point> points;

    private static final Event<Consumer<Shape>, Shape> onShapeModifiedEvent = new ActionEvent<>();

    public Shape() {
        this.id = ++globalId;
        points = new ArrayList<>();
    }

    public Shape(List<Point> points) {
        this();
        setPoints(points);
    }

    public int getId() {
        return id;
    }

    public List<Point> getPoints() {
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : points) {
            pointsCopy.add(point.clone());
        }
        return pointsCopy;
    }

    public void setPoints(List<Point> points) {
        this.points.clear();
        for (Point point : points) {
            this.points.add(point.clone());
        }
        onShapeModifiedEvent.invoke(this);
    }

    @Override
    public Shape clone() {
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : points) {
            pointsCopy.add(point.clone());
        }
        return new Shape(pointsCopy);
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


    public static void setupModificationListener(Consumer<Shape> listener) {
        onShapeModifiedEvent.subscribe(listener);
    }

    public static void removeModificationListener(Consumer<Shape> listener) {
        onShapeModifiedEvent.unsubscribe(listener);
    }
}
