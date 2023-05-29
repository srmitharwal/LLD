package com.personal.designPattern.parkinglot.bo;

import com.personal.designPattern.parkinglot.bo.parkingspot.BikeParkingSpot;
import com.personal.designPattern.parkinglot.bo.parkingspot.CarParkingSpot;
import com.personal.designPattern.parkinglot.bo.parkingspot.ParkingSpot;
import com.personal.designPattern.parkinglot.bo.vehicle.Bike;
import com.personal.designPattern.parkinglot.bo.vehicle.Car;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class Floor {
    private int id;

    private Set<ParkingSpot> availableCarParkingSpot;

    private Set<ParkingSpot> availableBikeParkingSpot;

    private Set<ParkingSpot> bookedCarSpots = null;

    private Set<ParkingSpot> bookedBikeSpots = null;


    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<ParkingSpot> getAvailableCarParkingSpot() {
        return availableCarParkingSpot;
    }

    public void setAvailableCarParkingSpot(Set<ParkingSpot> availableCarParkingSpot) {
        this.availableCarParkingSpot = availableCarParkingSpot;
    }

    public Set<ParkingSpot> getAvailableBikeParkingSpot() {
        return availableBikeParkingSpot;
    }

    public void setAvailableBikeParkingSpot(Set<ParkingSpot> availableBikeParkingSpot) {
        this.availableBikeParkingSpot = availableBikeParkingSpot;
    }

    public Set<ParkingSpot> getBookedCarSpots() {
        return bookedCarSpots;
    }

    public void setBookedCarSpots(Set<ParkingSpot> bookedCarSpots) {
        this.bookedCarSpots = bookedCarSpots;
    }

    public Set<ParkingSpot> getBookedBikeSpots() {
        return bookedBikeSpots;
    }

    public void freeBookingSpot(ParkingSpot parkingSpot) {
        if (parkingSpot.getClass().equals(CarParkingSpot.class)) {
            if (bookedCarSpots.contains(parkingSpot)) {
                bookedCarSpots.remove(parkingSpot);
            }
            availableCarParkingSpot.add(parkingSpot);
        } else if (parkingSpot.getClass().equals(BikeParkingSpot.class)) {
            if (bookedBikeSpots.contains(parkingSpot)) {
                bookedBikeSpots.remove(parkingSpot);
            }
            availableBikeParkingSpot.add(parkingSpot);
        }
        return;
    }

    public void setBookedBikeSpots(Set<ParkingSpot> bookedBikeSpots) {
        this.bookedBikeSpots = bookedBikeSpots;
    }

    public Ticket bookSpot(Vehicle vehicle) {
        ParkingSpot parkingSpot = null;
        if (vehicle.getClass().equals(Car.class)) {
            synchronized (Floor.class) {
                parkingSpot = availableCarParkingSpot.iterator().next();
                availableCarParkingSpot.remove(parkingSpot);
            }

            if (bookedCarSpots == null) {
                bookedCarSpots = new HashSet<>();
                bookedCarSpots.add(parkingSpot);
            }
        } else if (vehicle.getClass().equals(Bike.class)) {
            synchronized (Floor.class) {
                parkingSpot = availableBikeParkingSpot.iterator().next();
                availableBikeParkingSpot.remove(parkingSpot);
            }

            if (bookedBikeSpots == null) {
                bookedBikeSpots = new HashSet<>();
                bookedBikeSpots.add(parkingSpot);
            }
        }

        return generateTicket(parkingSpot, vehicle);
    }

    private Ticket generateTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        Ticket ticket= new Ticket();
        ticket.setTimestamp(System.currentTimeMillis());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicle(vehicle);
        ticket.setFloorId(this.id);
        return ticket;
    }
}
