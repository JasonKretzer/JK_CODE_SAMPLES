package com.kretzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jason for DI_IOC_Observer_JAVA.
 */
public class PolygonService implements Observer {

    private String serviceName;
    private AbstractPolygon polygon;

    public PolygonService(String initServiceName) throws InvalidArgumentException{
        setServiceName(initServiceName);
    }

    public void update(Observable o, Object arg) {
        System.out.println("ServiceName: " + serviceName + " -- Perimeter = " + ((AbstractPolygon) arg).getPerimeter());
    }

    public void setServiceName(String newServiceName) throws InvalidArgumentException {
        if(newServiceName == null) {
            throw new InvalidArgumentException(new String[] {"Service name must not be null."});
        }
        serviceName = newServiceName;
    }
}