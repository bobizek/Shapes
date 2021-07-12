package by.epam.shapes.dao.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    boolean add(T object);
    void addAll(List<T> objects);
    boolean remove(T object);
    List<T> quickSortById();
    List<T> quickSortByVertexX();
    List<T> quickSortByVertexY();

}
