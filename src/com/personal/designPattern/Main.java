package com.personal.designPattern;

import com.personal.designPattern.cor.logger.Logger;
import com.personal.designPattern.factory.lb.src.LeastConnectionLoadBalancer;
import com.personal.designPattern.factory.lb.src.LoadBalancer;
import com.personal.designPattern.state.vendingMachine.VendingMachine;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.CoinServiceImpl;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {

    public static void main(String[] args) throws Exception {
        //vendingMachineDriver();
        //loadBalancerDriver();
        //loggerDriver();
        zipTest();
    }

    private static void zipTest() throws IOException {
        String fileName = "/Users/srm/IdeaProjects/dcms/connectivity/connectors/build/connectorDependencies/AMAZON_S3_DATA_ASSET/connectivity-amazon-s3-connector-1.7.0.jar";
        JarFile jarFile = new JarFile(fileName);
//        Iterator iterator = jarFile.entries().asIterator();
//        while (iterator.hasNext()) {
//            JarEntry jarEntry = (JarEntry) iterator.next();
//           if (getExtension(jarEntry.getName()).equals("classpath")) {
//               InputStream input = jarFile.getInputStream(jarEntry);
//               process(input);
//           }
//        }

        Iterator iterator = jarFile.stream().iterator();
        while (iterator.hasNext()) {
            JarEntry jarEntry = (JarEntry) iterator.next();
            System.out.println(jarEntry.getName());
        }

//        JarEntry entry = jarFile.getJarEntry(args[1]);

        jarFile.close();
    }

    private static void process(InputStream input)
            throws IOException {
        InputStreamReader isr =
                new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

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
    private static String getExtension(String filename) {
        String [] fileParts = filename.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
