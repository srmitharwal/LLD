package com.personal.designPattern.state.vendingMachine.service;

import com.personal.designPattern.state.vendingMachine.bo.Coin;

import java.util.List;

public interface CoinService {
    public void addCoin(Coin coin);

    public void deleteCoin(Coin coin) throws Exception;

    public List<Coin> getAllCoins();

    public Coin getCoin(int coinId);
}
