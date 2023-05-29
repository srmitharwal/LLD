import PC.PC;
import com.personal.designPattern.cor.logger.Logger;
import com.personal.designPattern.factory.lb.src.LeastConnectionLoadBalancer;
import com.personal.designPattern.factory.lb.src.LoadBalancer;
import com.personal.designPattern.factory.lb.src.RoundRobinLoadBalancer;
import com.personal.designPattern.factory.lb.src.bo.Node;
import com.personal.designPattern.factory.lb.src.bo.Request;
import com.personal.designPattern.factory.lb.src.registry.ServiceRegistry;
import com.personal.designPattern.parkinglot.IParkingManager;
import com.personal.designPattern.parkinglot.ParkingManager;
import com.personal.designPattern.parkinglot.bo.Ticket;
import com.personal.designPattern.parkinglot.bo.vehicle.Bike;
import com.personal.designPattern.parkinglot.bo.vehicle.Car;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;
import com.personal.designPattern.rateLimiter.sw.UserBucketCreator;
import com.personal.designPattern.state.vendingMachine.VendingMachine;
import com.personal.designPattern.state.vendingMachine.bo.Product;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.CoinServiceImpl;
import com.personal.designPattern.state.vendingMachine.service.serviceImpl.ProductServiceImpl;
import lift.ElevatorManager;
import lift.models.Direction;
import lift.models.Floor;
import snakeandladder.models.Player;
import snakeandladder.models.response.CreateGameRespose;
import snakeandladder.models.response.MoveResponse;
import snakeandladder.service.DiceService;
import snakeandladder.service.DiceServiceImpl;
import snakeandladder.service.GameService;
import snakeandladder.service.GameServiceImpl;
import PC.OddEven;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {
         //vendingMachineDriver();
        //loadBalancerDriver();
        //loggerDriver();



//        JarEntry entry = jarFile.getJarEntry(args[1]);

        //RateLimiterDriver();
  //      snakeAndLadderDriver();

//        roundRobinLoadBalancer();

     //   liftTest();
       // testCSV();

     //   parkingTest();

       // testPC();

        testOddEven();

    }

    private static void testOddEven() {

        OddEven oddEven = new OddEven();
        Thread t1 = new Thread(() -> {
            try {
                oddEven.printEvenNo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                oddEven.printOddNo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    private static void testPC() throws InterruptedException {
        PC pc = new PC(1);
        Thread t1 = new Thread(() -> {
            try {
                producer(pc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                consumer(pc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();



        t1.join();
        t2.join();
    }

    private static void consumer(PC pc) throws InterruptedException {
        while(true) {
            pc.consume();
        }
    }

    private static void producer(PC pc) throws InterruptedException {
        int x = 0;
        while(true) {
            pc.produce(x++);
        }
    }

    private static void testCSV() throws IOException {
        //String file = new String("/Users/srm/Downloads/DEFAULT_CONNECTION_DATA_20230516102153095_crmanalyticsam_activitiesam_activity-5839894-20230516_102159.csv");

        String file = "/Users/srm/Downloads/DEFAULT_CONNECTION_DATA_20230516140307174_crmanalyticsam_activitiesam_activity-5840328-20230516_140322.csv";
        String delimiter = ",";
        String line;
        List<List<String>> lines = new ArrayList();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(delimiter));
                lines.add(values);
            }
            lines.forEach(l -> System.out.println(l.get(9)));
        }

}

    private static void parkingTest() {
        IParkingManager parkingManager = new ParkingManager();

        parkingManager.init();

        Set<Ticket>tickets = new HashSet<>();

        for(int i = 0; i < 2; i++) {
            if (i == 0 ) {
                new Thread(() -> parkingExecution(parkingManager, tickets)).start();
            } else {
                new Thread(() -> freeSlots(parkingManager, tickets)).start();
            }
        }

        System.out.println("init done");
    }

    private static void freeSlots(IParkingManager parkingManager, Set<Ticket> tickets) {

        while(true) {
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!tickets.isEmpty()) {
                Ticket ticket = tickets.iterator().next();
                tickets.remove(ticket);
                parkingManager.exitParking(ticket);
            }
        }
    }

    private static void parkingExecution(IParkingManager parkingManager, Set<Ticket> tickets) {

        while(true) {

            Random random = new Random();
            int no = random.nextInt(2);
            if(no == 0) {
                String carNo = UUID.randomUUID().toString();
                Vehicle vehicle = new Car(carNo, "white", "Car");

                Ticket ticket = parkingManager.bookParking(vehicle);

                if(ticket != null)tickets.add(ticket);

            } else {
                String bikeNo = UUID.randomUUID().toString();
                Vehicle vehicle = new Bike(bikeNo, "white", "Bike");
                Ticket ticket = parkingManager.bookParking(vehicle);

                if(ticket != null) tickets.add(ticket);
            }

            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private static void liftTest() {

        ElevatorManager elevatorManager = ElevatorManager.getInstance();
        elevatorManager.init(3,10);

       for (int i = 0; i < 2; i++) {
           new Thread(() -> excecution(elevatorManager)).start();

       }
    }


    private static void excecution(ElevatorManager elevatorManager) {
        while(true) {
            Random random = new Random();
            int floorId = 1 + random.nextInt(elevatorManager.getFloorList().size());
            int up = random.nextInt(2);
            Direction direction;
            if (up == 0) direction = Direction.UP;
            else direction = Direction.DOWN;
            System.out.println("Thread name: "+ Thread.currentThread().getId()+ "  floorId: " + floorId);
            Floor floor = elevatorManager.getFloorList().get(floorId - 1);
            floor.getExternalButton().pressButton(floorId, direction);

            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }



    private static void roundRobinLoadBalancer() {

        Node node1 = new Node(123, "192.168.3.1", 0);

        Node node2 = new Node(123, "192.168.3.2", 0);
        Node node3 = new Node(123, "192.168.3.3", 0);


        ServiceRegistry.getInstance().addService(node1, "profile");
        ServiceRegistry.getInstance().addService(node2, "profile");
        ServiceRegistry.getInstance().addService(node3, "profile");

        LoadBalancer loadBalancer = new RoundRobinLoadBalancer();

//        Executor executor = Executors.newFixedThreadPool(10);
//
//        Executors.newFixedThreadPool().;

        //Request request = new Request(1, "profile");

        ExecutorService executorService =  Executors.newFixedThreadPool(50);


        for(int i = 0; i < 50; i++) {
            Request request = new Request(i, "profile");
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        Node node = loadBalancer.getNode(request);
                        System.out.println("Request id" + request.getId() + " served by :" + node.getIp());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


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
    private static String getExtension(String filename) {
        String [] fileParts = filename.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
