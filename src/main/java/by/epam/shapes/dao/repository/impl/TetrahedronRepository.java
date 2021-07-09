package by.epam.shapes.dao.repository.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.dao.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TetrahedronRepository implements Repository<RegularTetrahedron> {

    private static Repository<RegularTetrahedron> instance;

    public static Repository<RegularTetrahedron> getInstance() {
        return instance != null ? instance : (instance = new TetrahedronRepository());
    }

    protected TetrahedronRepository() {
        shapeList = new ArrayList<>();
    }

    protected final List<RegularTetrahedron> shapeList;


    public List<RegularTetrahedron> findAll() {
        ArrayList<RegularTetrahedron> clonedShapesList = new ArrayList<>();
        for (RegularTetrahedron shape : shapeList) {
            clonedShapesList.add(shape.clone());
        }
        return clonedShapesList;
    }

    private List<RegularTetrahedron> findShapesMatching(Predicate<RegularTetrahedron> predicate) {
        return findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Optional<RegularTetrahedron> findById(int id) {
        return findAll()
                .stream()
                .filter(shape -> shape.getId() == (id)).findFirst();
    }


    public List<RegularTetrahedron> findShapesInFirstOctant() {
        List<RegularTetrahedron> shapesInFirstOctant = new ArrayList<>();
        for (RegularTetrahedron shape : findAll()) {
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

    public void add(RegularTetrahedron shape) {
        shapeList.add(shape);
    }

    public void addAll(List<RegularTetrahedron> shapes) {
        shapes.forEach(this::add);
    }

    public void remove(RegularTetrahedron shape) {
        shapeList.remove(shape);
    }

    public void sort() { //// TODO: 9.07.21  

    }

}
