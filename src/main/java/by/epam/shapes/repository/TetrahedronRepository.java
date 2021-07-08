package by.epam.shapes.repository;

import by.epam.shapes.entity.RegularTetrahedron;

public class TetrahedronRepository extends ShapeRepository {

    public TetrahedronRepository() {
        super();
    }

    public TetrahedronRepository(RegularTetrahedron... shapes) {
        super(shapes);
    }

}
