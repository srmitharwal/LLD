package com.personal.designPattern.parkinglot;

import com.personal.designPattern.parkinglot.bo.Ticket;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;

public interface IParkingManager {

    public void init();

    public Ticket bookParking(Vehicle vehicle);

    public boolean exitParking(Ticket ticket);

    public int availableParkingsForBikes();

    public int availableParkingsForCars();
}
