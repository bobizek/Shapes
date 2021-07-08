package by.epam.shapes.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void add(T object);
    void remove(T object);
}
