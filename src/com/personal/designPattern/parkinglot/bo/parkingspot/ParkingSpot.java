package com.personal.designPattern.parkinglot.bo.parkingspot;

import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

public class ParkingSpot {
    private int id;

    private Vehicle vehicle;

    private boolean isEmpty;

    public ParkingSpot(int id) {
        this.id = id;
        this.isEmpty = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
