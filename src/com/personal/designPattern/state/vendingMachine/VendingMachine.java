package com.personal.designPattern.state.vendingMachine;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.CoinService;
import com.personal.designPattern.state.vendingMachine.service.ProductService;
import com.personal.designPattern.state.vendingMachine.states.CoinInsertionState;
import com.personal.designPattern.state.vendingMachine.states.ItemSelectionState;
import com.personal.designPattern.state.vendingMachine.states.State;

import java.util.List;

public class VendingMachine {
    State state;
    public ProductService productService;
    public CoinService coinService;
    private Product selectedProduct;
    private Coin insertedCoin;
    public VendingMachine(ProductService productService, CoinService coinService) {
        this.productService = productService;
        this.coinService = coinService;
        this.state = new ItemSelectionState(this);
    }


    public List<Product> showAllproducts() {

        return productService.getAllProducts();
    }

    public Product selectItem(int productId){
        setSelectedProduct(this.state.selectItem(productId));
        return getSelectedProduct();
    }

    public void changeState(State state){
        this.state = state;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Coin getInsertedCoin() {
        return insertedCoin;
    }

    public void setInsertedCoin(Coin insertedCoin) {
        this.insertedCoin = insertedCoin;
    }

    private void insertCoin(int coin){
        this.state.insertCoin(coin);
    }

    public List<Coin> dispenseCoin(){
        return this.state.dispenseCoin();
    }

    public Product dispenseItem() {
        return this.state.dispenseItem();
    }

//    private void cancelOrder(){
//        this.state.cancelOrder();
//    }

    public boolean insertCoinOrCancel(int value){
        if (value  != 0) {
            System.out.println("Thanks for inserting coin. please collect change");
            this.changeState(new CoinInsertionState(this));
            insertCoin(value);
            return true;
        }
        else this.changeState(new ItemSelectionState(this));
        return false;
    }
}
