package snakeandladder.service;

import java.util.Random;

public class DiceServiceImpl implements DiceService{

    public int roll() {
        Random random = new Random();
        return 1 + random.nextInt(6);
    }

}
