package com.personal.designPattern.state.vendingMachine.states;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.VendingMachine;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DispenseCoinState implements State {
    VendingMachine vendingMachine;

    public DispenseCoinState (VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }
    @Override
    public Product selectItem(int productId) {
        return null;
    }

    @Override
    public String insertCoin(int coin) {
        return null;
    }

    @Override
    public Product dispenseItem() {
        return null;
    }

    @Override
    public List<Coin> dispenseCoin() {
        int coinToDispense = vendingMachine.getSelectedProduct().getPrice() - vendingMachine.getInsertedCoin().getValue();
        List<Coin>change = makeChange(coinToDispense);
        this.vendingMachine.changeState(new DispenseItemState(this.vendingMachine));
        return change;
    }

    @Override
    public void cancelOrder() {

    }

    private List<Coin> makeChange(int coinToDispense) {
        List<Coin> coinList = this.vendingMachine.coinService.getAllCoins();
        Collections.sort(coinList, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        return coinList;
    }
}
