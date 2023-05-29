package lift;

import lift.models.*;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {

    private static ElevatorManager elevatorManager = null;

    private  static List<ElevatorCartController> elevatorCartControllerList;
    private static List<Floor> floorList;

    private ElevatorManager(){

    }

    public static ElevatorManager getInstance () {

        if (elevatorManager == null) {

            synchronized (ElevatorManager.class) {
                if (elevatorManager == null) {
                    elevatorManager = new ElevatorManager();
                }
            }
        }
        return elevatorManager;
    }

    public void init(int noOfElevator, int noOfFloor) {

        elevatorCartControllerList = new ArrayList<>();
        initElevator(noOfElevator);
        initFloors(noOfFloor);
    }

    private static void initFloors(int noOfFloor) {
        floorList = new ArrayList<>();
        for (int i = 0; i < noOfFloor; i++) {

            ExternalButtonDispatcher externalButtonDispatcher = new ExternalButtonDispatcher(elevatorCartControllerList);
            ExternalButton externalButton = new ExternalButton(externalButtonDispatcher);

            Display display = new Display();
            display.setDirection(Direction.IDLE);
            display.setFloor(i);
            Floor floor =  new Floor(1, display, externalButton);
            floorList.add(floor);
        }

    }


    private static void initElevator(int noOfElevator) {

        for(int i = 0; i < noOfElevator; i++) {
            Display display = new Display();
            display.setDirection(Direction.IDLE);
            display.setFloor(1);

            ElevatorCart elevatorCart = new ElevatorCart(i+1, display);
            ElevatorCartController  elevatorCartController = new ElevatorCartController(elevatorCart);

            InternalButtonDispatcher internalButtonDispatcher = new InternalButtonDispatcher(elevatorCartController);
            InternalButton internalButton = new InternalButton(internalButtonDispatcher);

            elevatorCart.setInternalButton(internalButton);

            elevatorCartControllerList.add(elevatorCartController);

        }
    }

    public  List<Floor> getFloorList() {
        return floorList;
    }
}
