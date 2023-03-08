package com.personal.designPattern.state.vendingMachine.states;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.VendingMachine;

import java.util.List;

public class DispenseItemState implements State {
    VendingMachine vendingMachine;

    public DispenseItemState(VendingMachine vendingMachine){
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
        System.out.println("dispensing item");
        Product product = this.vendingMachine.productService.removeProduct(this.vendingMachine.getSelectedProduct().getId());
        this.vendingMachine.changeState(new ItemSelectionState(vendingMachine));
        return product;
    }

    @Override
    public List<Coin> dispenseCoin() {
        return null;
    }

    @Override
    public void cancelOrder() {

    }
}
