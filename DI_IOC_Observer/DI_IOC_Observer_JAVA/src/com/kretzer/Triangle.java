package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public class Triangle extends AbstractPolygon {
    public Triangle(double initSideLength) throws InvalidArgumentException{
        sides = 3;
        setSideLength(initSideLength);
    }
}
