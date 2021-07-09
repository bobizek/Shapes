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
        TetrahedronSourceValidator validator = new TetrahedronSourceValidator();
        PointsParser parser = new PointsParser();
        PointReader reader = new PointReader("src//main//resources//textfiles//file.txt", validator);
        ShapeCreator creator = new ShapeCreator(reader, parser);
        List<Shape> shapes = creator.createShapes();

        CalculationsWarehouse warehouse = CalculationsWarehouse.getInstance();

        Repository<Shape> shapeRepository = RepositoryFactory.of(ShapeType.SHAPE);
        shapeRepository.addAll(shapes);

        Repository<RegularTetrahedron> tetrahedronRepository = RepositoryFactory.of(ShapeType.TETRAHEDRON);
        tetrahedronRepository.addAll(shapes.stream()
                .filter(shape -> BaseShapeService.of(ShapeType.TETRAHEDRON).isRegularTetrahedron(shape.getPoints()))
                .map(shape -> (RegularTetrahedron)shape)
                .collect(Collectors.toList()));

        System.out.println(shapeRepository.findAll());
        System.out.println(tetrahedronRepository.findAll());

        System.out.println(warehouse.getAreas());
        System.out.println(warehouse.getVolumes());
    }
}