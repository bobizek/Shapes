package by.epam.shapes.repository;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShapeRepository {

    private final List<Shape> shapeList = new ArrayList<>();

    public ShapeRepository() { }

    public static ShapeRepository getInstance() {
        return InstanceHandler.INSTANCE;
    }

    public List<Shape> getShapesList() {
        ArrayList<Shape> clonedShapesList = new ArrayList<>();
        for (Shape shape : shapeList) {
            try {
                clonedShapesList.add(shape.clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        return clonedShapesList;
    }

    private List<Shape> getShapesMatching(Predicate<Shape> predicate) {
        return getShapesList()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Shape> findById(int id) {
        return getShapesMatching(shape -> shape.getId() == (id));
    }

    public List<Shape> findByName(String name) {
        return getShapesMatching(shape -> shape.getName().equalsIgnoreCase(name));
    }

    public List<Shape> findShapesInFirstOctant() {
        List<Shape> shapesInFirstOctant = new ArrayList<>();
        for (Shape shape : getShapesList()) {
            for (Point point : shape.getPoints()) {
                if (point.getX() > 0 &&
                    point.getY() > 0 &&
                    point.getZ() > 0) {
                    shapesInFirstOctant.add(shape);
                }
            }
        }
        return shapesInFirstOctant;
    }

    public void addShape(Shape shape) {
        shapeList.add(shape);
    }

    public void removeShape(Shape shape) {
        shapeList.remove(shape);
    }

    public void sort() {

    }

    private static class InstanceHandler {
        public static final ShapeRepository INSTANCE = new ShapeRepository();
    }
 }
