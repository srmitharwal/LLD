package com.personal.designPattern.parkinglot.factory;

import com.personal.designPattern.parkinglot.bo.vehicle.Bike;
import com.personal.designPattern.parkinglot.bo.vehicle.Car;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

public class FloorSelectionFactory {

    private static FloorSelectionFactory floorSelectionFactory = null;

    private FloorSelectionFactory () {

    }

    public static FloorSelectionFactory getInstance() {

        if(floorSelectionFactory == null ) {
            synchronized (FloorSelectionFactory.class) {
                if(floorSelectionFactory == null) {
                    floorSelectionFactory = new FloorSelectionFactory();
                }
            }
        }
        return floorSelectionFactory;
    }

    public IFloorSelection getFloorSelection(Vehicle vehicle) {

        if (vehicle.getClass().equals(Car.class)) {
            return new CarFloorSelection();
        } else if(vehicle.getClass().equals(Bike.class)) {
            return new BikeFloorSelection();
        }

        return null;
    }

}
