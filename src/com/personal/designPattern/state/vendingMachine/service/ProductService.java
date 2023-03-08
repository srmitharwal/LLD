package com.personal.designPattern.state.vendingMachine.service;

import com.personal.designPattern.state.vendingMachine.bo.Product;

import java.util.List;

public interface ProductService {
    public void addProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProduct(int productId);

    public Product removeProduct(int productId);
}
