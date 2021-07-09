package by.epam.shapes.util.warehouse.impl;

import by.epam.shapes.entity.RegularTetrahedron;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.helper.ShapeType;
import by.epam.shapes.util.BaseShapeService;
import by.epam.shapes.util.ShapeService;
import by.epam.shapes.util.warehouse.Warehouse;

import java.util.HashMap;
import java.util.Map;

public class CalculationsWarehouse implements Warehouse {

    private final Map<Integer, Double> areas = new HashMap<>();
    private final Map<Integer, Double> volumes = new HashMap<>();

    private static CalculationsWarehouse instance;

    public static CalculationsWarehouse getInstance() {
        return instance != null ? instance : (instance = new CalculationsWarehouse());
    }

    private CalculationsWarehouse() {
        Shape.setupModificationListener(this::recalculateDataForShape);
    }

    public Map<Integer, Double> getAreas() {
        return areas;
    }
    public Map<Integer, Double> getVolumes() {
        return volumes;
    }

    @Override
    protected void finalize() {
        Shape.removeModificationListener(this::recalculateDataForShape);
    }

    private void recalculateDataForShape(Shape shape) {
        if(shape.getClass() == RegularTetrahedron.class) {
            ShapeService<Object> shapeService = BaseShapeService.of(ShapeType.TETRAHEDRON);
            int key = shape.getId();
            areas.remove(key);
            areas.put(key, shapeService.countArea(shape));
            volumes.remove(key);
            volumes.put(key, shapeService.countVolume(shape));
        }
    }

}
