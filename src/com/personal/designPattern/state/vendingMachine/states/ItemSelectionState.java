package com.personal.designPattern.state.vendingMachine.states;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.VendingMachine;

import java.util.List;
import java.util.Scanner;

public class ItemSelectionState implements State {
    VendingMachine vendingMachine;

    public ItemSelectionState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
        this.vendingMachine.setInsertedCoin(null);
        this.vendingMachine.setSelectedProduct(null);
    }


    @Override
    public Product selectItem(int productId) {
        Product product = vendingMachine.productService.getProduct(productId);
        vendingMachine.setSelectedProduct(product);
        vendingMachine.productService.removeProduct(productId);
        return product;
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
        return null;
    }

    @Override
    public void cancelOrder() {

    }
}
