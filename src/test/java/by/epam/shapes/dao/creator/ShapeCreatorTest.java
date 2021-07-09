package by.epam.shapes.dao.creator;

import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.exception.ReaderException;
import by.epam.shapes.dao.parser.impl.PointsParser;
import by.epam.shapes.dao.reader.impl.PointReader;
import by.epam.shapes.dao.repository.Repository;
import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.dao.repository.factory.RepositoryFactory;
import by.epam.shapes.util.BaseShapeService;
import by.epam.shapes.dao.validator.impl.TetrahedronSourceValidator;
import by.epam.shapes.util.warehouse.Warehouse;
import by.epam.shapes.util.warehouse.impl.CalculationsWarehouse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class ShapeCreatorTest {
    @Test
    void createShape() throws ReaderException {

    }
}