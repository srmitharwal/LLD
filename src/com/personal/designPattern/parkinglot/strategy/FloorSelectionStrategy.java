package com.personal.designPattern.parkinglot.strategy;

import com.personal.designPattern.parkinglot.bo.Floor;

import java.util.List;

public interface FloorSelectionStrategy {

    public int selectFloor(List<Floor> floors);
}
