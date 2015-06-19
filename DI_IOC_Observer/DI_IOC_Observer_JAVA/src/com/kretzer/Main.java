package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Main {

    public static void main(String[] args) {
        try {
            Triangle triangle = new Triangle(5.0);
            Hexagon hexagon = new Hexagon(3);

            PerimeterCalculator calculator = new PerimeterCalculator();

            PolygonService triObserver1 = new PolygonService("TriObs1");
            PolygonService triObserver2 = new PolygonService("TriObs2");
            triangle.attach(triObserver1);
            triangle.attach(triObserver2);

            PolygonService hexObserver1 = new PolygonService("HexObs1");
            PolygonService hexObserver2 = new PolygonService("HexObs2");
            hexagon.attach(hexObserver1);
            hexagon.attach(hexObserver2);

            System.out.println("INITIAL TRIANGLE");
            System.out.println("triangle perimeter: " + triangle.getPerimeter());
            System.out.println("triangle sides: " + triangle.getNumberOfSides());
            System.out.println("triangle side length: " + triangle.getSideLength());
            System.out.println("Triangle From the Calculator IoC: " + calculator.calculate(triangle));

            System.out.println("==================================");

            System.out.println("INITIAL HEXAGON");
            System.out.println("hexagon perimeter: " + hexagon.getPerimeter());
            System.out.println("hexagon sides: " + hexagon.getNumberOfSides());
            System.out.println("hexagon side length: " + hexagon.getSideLength());
            System.out.println("Hexagon From the Calculator IoC: " + calculator.calculate(hexagon));

            System.out.println("==================================");
            System.out.println("==================================");
            System.out.println("==================================");
            System.out.println("Making Changes to SideLength so that Update will fire.");
            triangle.setSideLength(10);
            hexagon.setSideLength(10);

            System.out.println("==================================");
            System.out.println("==================================");
            System.out.println("==================================");

            System.out.println("FINAL TRIANGLE");
            System.out.println("triangle perimeter: " + triangle.getPerimeter());
            System.out.println("triangle sides: " + triangle.getNumberOfSides());
            System.out.println("triangle side length: " + triangle.getSideLength());
            System.out.println("Triangle From the Calculator IoC: " + calculator.calculate(triangle));

            System.out.println("==================================");

            System.out.println("FINAL HEXAGON");
            System.out.println("hexagon perimeter: " + hexagon.getPerimeter());
            System.out.println("hexagon sides: " + hexagon.getNumberOfSides());
            System.out.println("hexagon side length: " + hexagon.getSideLength());
            System.out.println("Hexagon From the Calculator IoC: " + calculator.calculate(hexagon));




        } catch (InvalidArgumentException iae) {
            iae.printStackTrace();
        }

    }
}
