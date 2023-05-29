package com.personal.designPattern.parkinglot.bo;

import com.personal.designPattern.parkinglot.bo.parkingspot.ParkingSpot;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

import java.util.UUID;

public class Ticket {
    private String id;

    private Vehicle vehicle;

    private ParkingSpot parkingSpot;

    private long timestamp;

    private int floorId;

    public Ticket() {
        this.id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
