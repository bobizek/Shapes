package by.epam.shapes.dao.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void add(T object);
    void addAll(List<T> objects);
    void remove(T object);
}
