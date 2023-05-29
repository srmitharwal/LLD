package com.personal.designPattern.parkinglot.bo.vehicle;

public class Vehicle {

    private String vehicleNo;

    private String vehicleColor;

    private String type;

    public Vehicle(String vehicleNo, String vehicleColor, String type) {
        this.vehicleColor = vehicleColor;
        this.vehicleNo = vehicleNo;
        this.type = type;
    }


    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
