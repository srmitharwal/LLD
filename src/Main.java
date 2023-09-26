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
import splitwise.models.*;
import splitwise.models.User;
import splitwise.services.*;
import calendar.models.*;
import calendar.services.*;
import splitwise.services.UserService;
import splitwise.services.UserServiceImpl;

import java.io.*;
import java.time.LocalDateTime;
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

       // testOddEven();

        testSplitWise();
 //       testCalendar();

    }

    private static void testCalendar() {
        calendar.services.UserService userService = new calendar.services.UserServiceImpl();

        EventService eventService = new EventServiceImpl();

        calendar.models.User user1 = new calendar.models.User("srm1","srm1@gmail.com");
        calendar.models.User user2 = new calendar.models.User("srm2","srm1@gmail.com");
        calendar.models.User user3 = new calendar.models.User("srm3","srm1@gmail.com");
        calendar.models.User user4 = new calendar.models.User("srm4","srm1@gmail.com");

        user1 = userService.createUser(user1);
        user2 = userService.createUser(user2);
        user3 = userService.createUser(user3);
        user4 = userService.createUser(user4);


        Map<calendar.models.User,Boolean> userMap = new HashMap<>();
        userMap.put(user1, true);
        userMap.put(user2,false);
        userMap.put(user3,false);

        Event event1 = new Meeting("meeting1", user1, LocalDateTime.now(), LocalDateTime.now().plusHours(4),userMap);
        event1 = eventService.createEvent(event1);


        userMap = new HashMap<>();
        userMap.put(user2, true);
        userMap.put(user1,false);
        userMap.put(user4,false);
        Event event2 = new Meeting("meeting1", user2, LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(7), userMap);
        event2 = eventService.createEvent(event2);


        userMap = new HashMap<>();
        userMap.put(user3, true);
        userMap.put(user2,false);
        Event event3 = new Meeting("meeting1", user2, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(7), userMap);
        event2 = eventService.createEvent(event3);


        List<Event> events = eventService.getEvents(user4,LocalDateTime.now().minusMinutes(30),LocalDateTime.now().plusHours(5));

        System.out.println(events.size());

    }

    private static void testSplitWise() {

        User user1 = new User("srm1","srm1@gmail.com");
        User user2 = new User("srm2", "srm2@gmail.com");
        User user3 = new User("srm3", "srm3@gmail.com");



        UserService userService = new UserServiceImpl();
        GroupService groupService = new GroupServiceImpl();

        ExpenseService expenseService = new ExpenseServiceImpl( groupService, userService);

        user1 = userService.createUser(user1);
        user2 = userService.createUser(user2);
        user3 = userService.createUser(user3);
        Expense expense;
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        Group group1 = new Group("g1", userList);
        group1 = groupService.createGroup(group1);
        List<Double>priceList = new ArrayList<>();
        priceList.add(10.0);
        priceList.add(10.0);
        priceList.add(0.0);
        expense = addGroupExpense(group1, 20, priceList, user1, expenseService);
       // 90 //-20 // -70
        priceList = new ArrayList<>();
        priceList.add(0.0);
        priceList.add(0.0);
        priceList.add(20.0);
         expense = addGroupExpense(group1, 20, priceList, user2, expenseService);


        priceList = new ArrayList<>();
        priceList.add(10.0);
        priceList.add(0.0);
        priceList.add(80.0);
        expense = addGroupExpense(group1, 90, priceList, user3, expenseService);
        group1 = groupService.getGroup(group1.getGroupId());
       printMap1(group1.getBalanceMap());
       //a->b = 10 b-c = 20 c- a= 10

        Expense tmp = addUserExpense(user1, user2, 100, 30, 70, expenseService);
        expense = addUserExpense(user1, user2, 101, 20.5, 80.5, expenseService);
        expense = addUserExpense(user3, user1, 10, 1, 9, expenseService);
        expenseService.deleteExpense(tmp.getExpenseId());
//        expense = addUserExpense(user2, user3, 5, 2, 3, expenseService);




        user1 = userService.getUser(user1.getUserId());
        user2 = userService.getUser(user2.getUserId());
        user3 = userService.getUser(user3.getUserId());

        System.out.println(user1.getBalance());
        printMap(user1.getUserBalanceMap());

        System.out.println(user2.getBalance());
        printMap(user2.getUserBalanceMap());

        System.out.println(user3.getBalance());
        printMap(user3.getUserBalanceMap());

    }




    private static void printMap1(Map<String, List<UserBalanceInfo>> userBalanceMap) {
        for(Map.Entry<String, List<UserBalanceInfo>> entry : userBalanceMap.entrySet()) {
            System.out.print(entry.getKey() + " " );
            for(int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(entry.getValue().get(i).getDebitor() + " " + entry.getValue().get(i).getBalance());
            }
        }
    }

    private static void printMap(Map<User, Double> userBalanceMap) {
        for(Map.Entry<User, Double> entry : userBalanceMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }
    }

    private static Expense addGroupExpense(Group group, double amount, List<Double> shareList, User user1, ExpenseService expenseService) {

        Expense expense = new Expense();
        expense.setExpenseType(ExpenseType.GROUP);
        expense.setExpenseName("g1->expense");
        expense.setCreditor(user1);
        expense.setAmount(amount);
        expense.setGroupId(group.getGroupId());
        expense.setUsersList(group.getUserList());
        Map<User, Double> shareMap = new HashMap<>();
        for (int i = 0 ; i < group.getUserList().size(); i++) {
            shareMap.put(group.getUserList().get(i), shareList.get(i));
        }
        expense.setShareMap(shareMap);
        return expenseService.addExpense(expense);
    }
    private static Expense addUserExpense(User user1, User user2, double amount, double first, double second, ExpenseService expenseService) {
        Expense expense = new Expense();
        expense.setCreditor(user1);
        List<User> userList = new ArrayList<>();
        userList.add(user2);
        expense.setUsersList(userList);
        expense.setAmount(amount);
        expense.setExpenseType(ExpenseType.USER);

        Map<User, Double> shareMap = new HashMap<>();
        shareMap.put(user1, first);
        shareMap.put(user2, second);
        expense.setShareMap(shareMap);
        return expenseService.addExpense(expense);
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
    //msgId, sender, recierver, content, bloburl, type, timestamp
    //

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
