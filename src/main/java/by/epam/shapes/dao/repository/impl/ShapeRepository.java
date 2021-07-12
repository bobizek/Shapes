package by.epam.shapes.dao.repository.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.dao.repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
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

    private List<Shape> findShapesMatching(Predicate<Shape> predicate) { //never used
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

    public boolean add(Shape shape) {
        return shapeList.add(shape);
    }

    public void addAll(List<Shape> shapes) {
        shapes.forEach(this::add);
    }

    public boolean remove(Shape shape) {
        return shapeList.remove(shape);
    }

    @Override
    public List<Shape> quickSortById() {
        return (shapeList.size() == 1) ? shapeList : quickSort(shapeList, 0, shapeList.size() - 1,
                Comparator.comparingInt(Shape::getId));
    }

    @Override
    public List<Shape> quickSortByVertexX() {
        return (shapeList.size() == 1) ? shapeList : quickSort(shapeList, 0, shapeList.size() - 1,
                Comparator.comparingInt((Shape shape) -> shape.getPoints().get(0).getX()));
    }

    @Override
    public List<Shape> quickSortByVertexY() {
        return (shapeList.size() == 1) ? shapeList : quickSort(shapeList, 0, shapeList.size() - 1,
                Comparator.comparingInt((Shape shape) -> shape.getPoints().get(0).getY()));
    }

    private List<Shape> quickSort(List<Shape> shapeList, int leftMarker, int rightMarker, Comparator<Shape> comparator) {

        int left = leftMarker;
        int right = rightMarker;
        Shape pivot = shapeList.get((right + left) / 2);

        while (left <= right) {
            while (comparator.compare(pivot, shapeList.get(left)) > 0) {
                ++left;
            }

            while (comparator.compare(pivot, shapeList.get(right)) < 0) {
                --right;
            }

            if (left <= right) {
                if (left < right) {
                    Shape buf = shapeList.get(right);
                    shapeList.set(right, shapeList.get(left));
                    shapeList.set(left, buf);
                }
                ++left;
                --right;
            }
        }
        if (left < rightMarker) {
            quickSort(shapeList, left, rightMarker, comparator);
        }

        if (leftMarker < right) {
            quickSort(shapeList, leftMarker, right, comparator);
        }
        return shapeList;
    }
 }
