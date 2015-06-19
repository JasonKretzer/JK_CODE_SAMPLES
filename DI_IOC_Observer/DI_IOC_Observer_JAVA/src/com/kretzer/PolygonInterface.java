package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public interface PolygonInterface {
    public double getPerimeter();
    public double getSideLength();
    public void setSideLength(double newSideLength) throws InvalidArgumentException;
    public int getNumberOfSides();
}
