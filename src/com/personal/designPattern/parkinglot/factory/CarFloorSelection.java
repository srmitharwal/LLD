package com.personal.designPattern.parkinglot.factory;

import com.personal.designPattern.parkinglot.bo.Floor;
import com.personal.designPattern.parkinglot.strategy.DefaultFloorSelectionStrategyForCars;
import com.personal.designPattern.parkinglot.strategy.FloorSelectionStrategy;

import java.util.List;

public class CarFloorSelection implements IFloorSelection {

    FloorSelectionStrategy floorSelectionStrategy;

    public CarFloorSelection () {
        this.floorSelectionStrategy = new DefaultFloorSelectionStrategyForCars();
    }

    public CarFloorSelection (FloorSelectionStrategy floorSelectionStrategy) {
        this.floorSelectionStrategy = floorSelectionStrategy;
    }

    @Override
    public int selectFloor(List<Floor> floors) {
        return this.floorSelectionStrategy.selectFloor(floors);
    }
}
