package com.personal.designPattern.parkinglot.factory.costFactory;

import com.personal.designPattern.parkinglot.bo.Ticket;

import java.util.concurrent.TimeUnit;

public class BikeCostCalculator implements CostCalculator{

    @Override
    public int caculateCost(Ticket ticket) {
        long bookTime = ticket.getTimestamp();
        long currTime = System.currentTimeMillis();

        long hours = TimeUnit.MILLISECONDS.toHours(currTime - bookTime);

        return (int) hours*10;
    }
}
