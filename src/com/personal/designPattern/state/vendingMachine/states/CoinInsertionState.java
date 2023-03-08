package com.personal.designPattern.state.vendingMachine.states;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.VendingMachine;

import java.util.List;

public class CoinInsertionState implements State {
    VendingMachine vendingMachine;

    public CoinInsertionState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public Product selectItem(int productId) {
        return null;
    }

    @Override
    public String insertCoin(int value) {
        Coin coin = new Coin(value);
        this.vendingMachine.setInsertedCoin(coin);
        this.vendingMachine.coinService.addCoin(coin);
        this.vendingMachine.changeState(new DispenseCoinState(this.vendingMachine));
        return "CoinInserted";
    }

    @Override
    public Product dispenseItem() {
        return null;
    }

    @Override
    public List<Coin> dispenseCoin() {
        return null;
    }

    @Override
    public void cancelOrder() {

    }
}
