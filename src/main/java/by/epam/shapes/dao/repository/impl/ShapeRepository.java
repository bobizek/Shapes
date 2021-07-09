package by.epam.shapes.dao.repository.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.dao.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShapeRepository implements Repository<Shape> {

    private static Repository<Shape> instance;

    public static Repository<Shape> getInstance() {
        return instance != null ? instance : (instance = new ShapeRepository());
    }

    protected final List<Shape> shapeList;

    protected ShapeRepository() {
        shapeList = new ArrayList<>();
    }

    public List<Shape> findAll() {
        ArrayList<Shape> clonedShapesList = new ArrayList<>();
        for (Shape shape : shapeList) {
            clonedShapesList.add(shape.clone());
        }
        return clonedShapesList;
    }

    private List<Shape> findShapesMatching(Predicate<Shape> predicate) {
        return findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Optional<Shape> findById(int id) {
        return findAll()
                .stream()
                .filter(shape -> shape.getId() == (id)).findFirst();
    }


    public List<Shape> findShapesInFirstOctant() {
        List<Shape> shapesInFirstOctant = new ArrayList<>();
        for (Shape shape : findAll()) {
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

    public void add(Shape shape) {
        shapeList.add(shape);
    }

    public void addAll(List<Shape> shapes) {
        shapes.forEach(this::add);
    }

    public void remove(Shape shape) {
        shapeList.remove(shape);
    }

    public void sort() { // // TODO: 9.07.21  

    }
 }
