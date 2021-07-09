package by.epam.shapes.dao.repository.factory;

import by.epam.shapes.entity.Shape;
import by.epam.shapes.dao.repository.Repository;
import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.dao.repository.impl.ShapeRepository;
import by.epam.shapes.dao.repository.impl.TetrahedronRepository;

public class RepositoryFactory {

    public static <T extends Shape> Repository<T> of(ShapeType type) {
        return switch (type) {
            case SHAPE -> (Repository<T>) ShapeRepository.getInstance();
            case TETRAHEDRON -> (Repository<T>) TetrahedronRepository.getInstance();
        };
    }
}
