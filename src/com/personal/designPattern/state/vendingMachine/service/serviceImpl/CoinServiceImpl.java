package com.personal.designPattern.state.vendingMachine.service.serviceImpl;

import com.personal.designPattern.state.vendingMachine.bo.Coin;
import com.personal.designPattern.state.vendingMachine.service.CoinService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinServiceImpl implements CoinService {
    Map<Integer, List<Coin>>coinMap;

    public CoinServiceImpl(){
        coinMap = new HashMap<>();
    }
    @Override
    public void addCoin(Coin coin) {

        if (!coinMap.containsKey(coin.getValue())) {
            coinMap.put(coin.getValue(), new ArrayList<>());
        }
        coinMap.get(coin.getValue()).add(coin);
        //coinMap.put(coin.getValue(), (coinMap.getOrDefault(coin.getValue(),new ArrayList<>())).add(coin));
    }

    @Override
    public void deleteCoin(Coin coin) throws Exception {
        if(coinMap.containsKey(coin.getValue())){
            if(coinMap.get(coin.getValue()).size() == 1) coinMap.remove(coin.getValue());
            else coinMap.get(coin.getValue()).remove(coinMap.get(coin.getValue()).size() - 1);
            return;
        } else {
            throw new Exception("Coin for the given value not found: {}");
        }
    }

    @Override
    public List<Coin> getAllCoins() {
        List<Coin> listOfCoins = new ArrayList<>();
        for(Map.Entry<Integer, List<Coin> >entry : coinMap.entrySet())listOfCoins.addAll(entry.getValue());
        return listOfCoins;
    }

    @Override
    public Coin getCoin(int coinId) {
        return null;
    }
}
