package com.personal.designPattern.state.vendingMachine.service.serviceImpl;

import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.ProductService;

import java.util.*;

public class ProductServiceImpl implements ProductService {
    Map<Integer, Product>productMap;
    Map<Integer, Integer>productCountMap;
    public ProductServiceImpl(){
        productMap = new HashMap<>();
        productCountMap = new HashMap<>();
    }

    @Override
    public void addProduct(Product product) {
        if (!productMap.containsKey(product.getId())) productMap.put(product.getId(), product);
        productCountMap.put(product.getId(), productCountMap.getOrDefault(product.getId(), 0) + product.getCount());
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productCountMap.entrySet()) {
            if (entry.getValue() > 0)productList.add(productMap.get(entry.getKey()));
        }
        return productList;
    }

    @Override
    public Product getProduct(int productId) {
        return productMap.get(productId);
    }

    @Override
    public Product removeProduct(int productId) {
        productCountMap.put(productId, productCountMap.get(productId) - 1);
        return productMap.get(productId);
    }
}
