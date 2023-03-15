package com.personal.designPattern;

import com.personal.designPattern.cor.logger.Logger;
import com.personal.designPattern.factory.lb.src.LeastConnectionLoadBalancer;
import com.personal.designPattern.factory.lb.src.LoadBalancer;
import com.personal.designPattern.rateLimiter.models.LimitResponse;
import com.personal.designPattern.rateLimiter.models.Request;
import com.personal.designPattern.rateLimiter.RateLimiter;
import com.personal.designPattern.rateLimiter.sw.SlidingRateLimiter;
import com.personal.designPattern.rateLimiter.sw.UserBucketCreator;
import com.personal.designPattern.state.vendingMachine.VendingMachine;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.CoinServiceImpl;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.ProductServiceImpl;
import snakeandladder.models.Player;
import snakeandladder.models.response.CreateGameRespose;
import snakeandladder.models.response.MoveResponse;
import snakeandladder.service.DiceService;
import snakeandladder.service.DiceServiceImpl;
import snakeandladder.service.GameService;
import snakeandladder.service.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {
         //vendingMachineDriver();
        //loadBalancerDriver();
        //loggerDriver();
        //RateLimiterDriver();
        snakeAndLadderDriver();

    }

    private static void snakeAndLadderDriver() {
        Player player1 = new Player(1, "player1");
        Player player2 = new Player(2, "player2");
        Player player3 = new Player(3, "player3");
        List<Player> players = new ArrayList<>();
        players.add(player2);
        players.add(player1);
        players.add(player3);
        GameService gameService = new GameServiceImpl();
        DiceService diceService = new DiceServiceImpl();
        CreateGameRespose createGameRespose = gameService.startGame(players,10, 10);
        System.out.println(createGameRespose.getMsg());

        Player currentPlayer = createGameRespose.getFirstPlayer();
        String gameId = createGameRespose.getGameId();

        while (true) {
            int score = diceService.roll();
            System.out.println("player :" + currentPlayer.getId() + " move: " + score);
            MoveResponse moveResponse = gameService.move(gameId, currentPlayer, score);
           // System.out.println("player :" + currentPlayerId + " move: " + score);
            if (moveResponse.getNextPlayer() == currentPlayer ){
                System.out.println("game finished");
                break;
            } else {
                currentPlayer = moveResponse.getNextPlayer();
            }
        }

    }

    private static void RateLimiterDriver() throws InterruptedException {
        UserBucketCreator userBucketCreator = new UserBucketCreator(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                userBucketCreator.grantAccess(1);
            });
        }
    }

//    public static void userBucketTest(UserBucketCreator userBucketCreator) throws InterruptedException {
//        LimitResponse limitResponse = userBucketCreator.grantAccess(1);
//        Thread.sleep(150);
//        //System.out.println(Thread.currentThread().getName() + " " +limitResponse.getMessage());
//    }

    private static void loggerDriver() {
        Logger logger = Logger.getLogger();
        logger.info("info log");
        logger.debug("debug log");
        logger.error("error log");
    }

    private static void loadBalancerDriver() throws Exception {


        LoadBalancer loadBalancer = new LeastConnectionLoadBalancer();

//        Request request1 = new Request("profile");
//        Request request2 = new Request("profile");
//
//        Request request3 = new Request("post");
//        int i = 0;
//        Executor executor = Executors.newFixedThreadPool(10);
//        executor.execute();
//        Executor executor = new Executor() {
//            @Override
//            public void execute(Runnable command) {
//                int i = new Random().nextInt();
//                Request request = new Request(i,"profile");
//                Node node = null;
//                try {
//                    node = loadBalancer.getNode(request);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                try {
//                    node.addRequest(request);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(" request no : " + request.getId() + " .... Node ip: " + node.getIp());
//                node.deleteRequest(request);
//            }
//        };


    }

    private static void vendingMachineDriver(){
        VendingMachine vendingMachine = new VendingMachine(new ProductServiceImpl(), new CoinServiceImpl());
        vendingMachine.productService.addProduct(new Product(1, "coke", 20,10, "coke "));
        vendingMachine.productService.addProduct(new Product(2, "water", 20,5, "water"));
        vendingMachine.productService.addProduct(new Product(3, "juice", 30, 12, "juice"));
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("-----Following Items are available. select any to buy");
            List<Product> productList = vendingMachine.showAllproducts();
            System.out.println("----" + "  ProductName " + "-----" + "  Price");
            for (Product product : productList) {
                System.out.println("----" + product.getName() + "-----" + product.getPrice());
            }
            int selectedProductId = in.nextInt();
            vendingMachine.selectItem(selectedProductId);

            System.out.println("you have selected Item. Please enter the coin or press 0 for cancel");
            int coin = new Scanner(System.in).nextInt();
            if(vendingMachine.insertCoinOrCancel(coin)){
                vendingMachine.dispenseCoin();
                vendingMachine.dispenseItem();
            }

        }
    }
}
