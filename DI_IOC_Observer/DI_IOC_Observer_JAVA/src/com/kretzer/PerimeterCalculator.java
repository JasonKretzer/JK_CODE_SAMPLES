package com.kretzer;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public class PerimeterCalculator {
    public double calculate(PolygonInterface polygon) {
        return polygon.getPerimeter();
    }
}
