package by.epam.shapes.factory;

import by.epam.shapes.entity.Shape;
import by.epam.shapes.repository.Repository;
import by.epam.shapes.repository.RepositoryType;
import by.epam.shapes.repository.ShapeRepository;
import by.epam.shapes.repository.TetrahedronRepository;

public class RepositoryFactory {

    public static Repository<? extends Shape> chooseRepo(RepositoryType type) {
        Repository<? extends Shape> repository = null;
        switch (type) {
            case SHAPE -> repository = new ShapeRepository();
            case TETRAHEDRON -> repository = new TetrahedronRepository();
        }
        return repository;
    }
}
