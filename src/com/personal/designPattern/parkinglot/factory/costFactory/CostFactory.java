package com.personal.designPattern.parkinglot.factory.costFactory;

import com.personal.designPattern.parkinglot.bo.vehicle.Bike;
import com.personal.designPattern.parkinglot.bo.vehicle.Car;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

public class CostFactory {

    public static CostFactory costFactory = null;

    private CostFactory() {

    }

    public static CostFactory getInstance() {
        if (costFactory == null ) {
            synchronized (CostFactory.class) {
                if(costFactory == null) {
                    costFactory = new CostFactory();
                }
            }
        }
        return costFactory;
    }

    public CostCalculator getCostCalculator(Vehicle vehicle) {

        if(vehicle.getClass().equals(Car.class)) {
                return new CarCostCalculator();
        } else if(vehicle.getClass().equals(Bike.class)) {
            return new BikeCostCalculator();
        }

        return null;
    }
}
