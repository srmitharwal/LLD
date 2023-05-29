package com.personal.designPattern.parkinglot.factory;

import com.personal.designPattern.parkinglot.bo.Floor;
import com.personal.designPattern.parkinglot.strategy.DefaultFloorSelectionStrategyForBikes;
import com.personal.designPattern.parkinglot.strategy.FloorSelectionStrategy;

import java.util.List;

public class BikeFloorSelection implements IFloorSelection {

    FloorSelectionStrategy floorSelectionStrategy;

    public BikeFloorSelection() {
        this.floorSelectionStrategy = new DefaultFloorSelectionStrategyForBikes();
    }

    public BikeFloorSelection(FloorSelectionStrategy floorSelectionStrategy) {
        this.floorSelectionStrategy = floorSelectionStrategy;
    }

    @Override
    public int selectFloor(List<Floor> floors) {
        return this.floorSelectionStrategy.selectFloor(floors);
    }
}
