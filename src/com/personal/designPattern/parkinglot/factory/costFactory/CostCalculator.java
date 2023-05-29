package com.personal.designPattern.parkinglot.factory.costFactory;

import com.personal.designPattern.parkinglot.bo.Ticket;

public interface CostCalculator {

    public int caculateCost(Ticket ticket);
}
