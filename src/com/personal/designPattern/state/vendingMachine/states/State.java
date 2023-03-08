package com.personal.designPattern.state.vendingMachine.states;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;

import java.util.List;

public interface State {

    public Product selectItem(int productId);

    public String insertCoin(int coin);

    public Product dispenseItem();

    public List<Coin> dispenseCoin();

    public void cancelOrder();
}
