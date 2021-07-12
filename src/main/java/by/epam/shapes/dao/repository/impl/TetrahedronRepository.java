package by.epam.shapes.dao.repository.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Tetrahedron;
import by.epam.shapes.dao.repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TetrahedronRepository implements Repository<Tetrahedron> {

    private static Repository<Tetrahedron> instance;

    public static Repository<Tetrahedron> getInstance() {
        return instance != null ? instance : (instance = new TetrahedronRepository());
    }

    protected TetrahedronRepository() {
        tetrahedronList = new ArrayList<>();
    }

    protected final List<Tetrahedron> tetrahedronList;


    public List<Tetrahedron> findAll() {
        ArrayList<Tetrahedron> clonedShapesList = new ArrayList<>();
        for (Tetrahedron shape : tetrahedronList) {
            clonedShapesList.add(shape.clone());
        }
        return clonedShapesList;
    }

    private List<Tetrahedron> findShapesMatching(Predicate<Tetrahedron> predicate) {
        return findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Optional<Tetrahedron> findById(int id) {
        return findAll()
                .stream()
                .filter(shape -> shape.getId() == (id)).findFirst();
    }


    public List<Tetrahedron> findShapesInFirstOctant() {
        List<Tetrahedron> shapesInFirstOctant = new ArrayList<>();
        for (Tetrahedron shape : findAll()) {
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

    public boolean add(Tetrahedron tetrahedron) {
        return tetrahedronList.add(tetrahedron);
    }

    public void addAll(List<Tetrahedron> shapes) {
        shapes.forEach(this::add);
    }

    public boolean remove(Tetrahedron shape) {
        return tetrahedronList.remove(shape);
    }

    @Override
    public List<Tetrahedron> quickSortByVertexX() {
        return (tetrahedronList.size() == 1) ? tetrahedronList : quickSort(this.tetrahedronList, 0, tetrahedronList.size() - 1,
                Comparator.comparingInt((Tetrahedron tet) -> tet.getTopPoint().getX()));
    }

    @Override
    public List<Tetrahedron> quickSortByVertexY() {
        return (tetrahedronList.size() == 1) ? tetrahedronList : quickSort(this.tetrahedronList, 0, tetrahedronList.size() - 1,
                Comparator.comparingInt((Tetrahedron tet) -> tet.getTopPoint().getY()));
    }

    @Override
    public List<Tetrahedron> quickSortById() {
        return (tetrahedronList.size() == 1) ? tetrahedronList : quickSort(this.tetrahedronList, 0, tetrahedronList.size() - 1, Comparator.comparingInt(Tetrahedron::getId));
    }

    private List<Tetrahedron> quickSort(List<Tetrahedron> tetrahedronList, int leftMarker, int rightMarker, Comparator<Tetrahedron> comparator) {

        int left = leftMarker;
        int right = rightMarker;
        Tetrahedron pivot = tetrahedronList.get((right + left) / 2);

        while (left <= right) {
            while (comparator.compare(pivot, tetrahedronList.get(left)) > 0) {
                ++left;
            }

            while (comparator.compare(pivot, tetrahedronList.get(right)) < 0) {
                --right;
            }

            if (left <= right) {
                if (left < right) {
                    Tetrahedron buf = tetrahedronList.get(right);
                    tetrahedronList.set(right, tetrahedronList.get(left));
                    tetrahedronList.set(left, buf);
                }
                ++left;
                --right;
            }
        }
        if (left < rightMarker) {
            quickSort(tetrahedronList, left, rightMarker, comparator);
        }

        if (leftMarker < right) {
            quickSort(tetrahedronList, leftMarker, right, comparator);
        }
        return tetrahedronList;
    }

}
