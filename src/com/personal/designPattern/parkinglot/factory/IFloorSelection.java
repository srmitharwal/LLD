package com.personal.designPattern.parkinglot.factory;

import com.personal.designPattern.parkinglot.bo.Floor;

import java.util.List;

public interface IFloorSelection {

    public int selectFloor(List<Floor> floors);
}
