package com.personal.designPattern.parkinglot.strategy;

import com.personal.designPattern.parkinglot.bo.Floor;

import java.util.List;

public class DefaultFloorSelectionStrategyForBikes implements FloorSelectionStrategy {
    @Override
    public int selectFloor(List<Floor> floors) {

        for (int i = 0; i < floors.size(); i++) {
            if(floors.get(i).getAvailableBikeParkingSpot().size() > 0) {
                return i;
            }
        }

        return -1;
    }
}
