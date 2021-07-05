package by.epam.shapes.validator.impl;

import by.epam.shapes.entity.Point;
import by.epam.shapes.entity.Shape;
import by.epam.shapes.util.ShapeService;
import by.epam.shapes.validator.TetrahedronValidator;

public class RegularTetrahedronValidatorImpl implements TetrahedronValidator {

    public boolean isValid(Point[] points) {
        //check if there are 4 points no less no more
        if (points.length != 4) {
            return false;
        }
        //check if all base points located in the same plane (all X-s are equals or all Z-s or all Y-s)
        return ShapeService.isRegularTetrahedron(points);
    }


}
