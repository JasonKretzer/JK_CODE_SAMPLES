package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public class Hexagon extends AbstractPolygon {
    public Hexagon(double initSideLength) throws InvalidArgumentException {
        sides = 6;
        setSideLength(initSideLength);
    }

}
