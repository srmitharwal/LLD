package com.personal.designPattern;

import com.personal.designPattern.factory.lb.src.LeastConnectionLoadBalancer;
import com.personal.designPattern.factory.lb.src.LoadBalancer;
import com.personal.designPattern.state.vendingMachine.VendingMachine;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.CoinServiceImpl;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.ProductServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        //vendingMachineDriver();
        loadBalancerDrive();



    }

    private static void loadBalancerDrive() throws Exception {


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
