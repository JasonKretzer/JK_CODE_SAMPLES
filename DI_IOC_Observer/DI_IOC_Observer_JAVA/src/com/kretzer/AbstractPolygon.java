package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public class AbstractPolygon extends Observable implements PolygonInterface {
    private double sideLength;
    protected int sides;

    public AbstractPolygon() {
        sideLength = 1.0E00;
        sides = 1;
    }

    public void attach(Observer o) throws InvalidArgumentException {
        if(o == null) {
            throw new InvalidArgumentException(new String[]{"Cannot attach a null observer."});
        }
        super.addObserver(o);
    }

    public void detach(Observer o) {
        super.deleteObserver(o);
    }

    public void notifyObservers() {
        super.notifyObservers(this);
    }



    public void setSideLength(double newSideLength) throws InvalidArgumentException {
        if(newSideLength <= 0.0) {
            throw new InvalidArgumentException(new String[]{"Side length must be a number greater than 0."});
        }
        sideLength = newSideLength;
        this.setChanged();
        this.notifyObservers();
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getPerimeter() {
        return ((double)sides*sideLength);
    }

    public int getNumberOfSides() {
        return sides;
    }

}