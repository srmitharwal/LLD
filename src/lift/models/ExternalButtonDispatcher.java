package lift.models;

import lift.strategy.DefaultLiftChooseStrategy;
import lift.strategy.LiftChooseStrategy;

import java.util.List;

public class ExternalButtonDispatcher {

    List<ElevatorCartController> elevatorCartControllerList;
    LiftChooseStrategy liftChooseStrategy;

    public ExternalButtonDispatcher(List <ElevatorCartController> elevatorCartControllerList) {
        this.elevatorCartControllerList = elevatorCartControllerList;
        this.liftChooseStrategy = new DefaultLiftChooseStrategy();
    }

    public ExternalButtonDispatcher(List <ElevatorCartController> elevatorCartControllerList,
                                    LiftChooseStrategy liftChooseStrategy) {
        this.elevatorCartControllerList = elevatorCartControllerList;
        this.liftChooseStrategy = liftChooseStrategy;
    }

    public void submitRequest(int floorId, Direction direction) {
        ElevatorCartController elevatorCartController = findServiceLift();
        System.out.println("Lift no: " + elevatorCartController.getElevatorCart().getId() + " serving the request, floorID: " + floorId);
        elevatorCartController.acceptRequest(floorId, direction);
    }

    private ElevatorCartController findServiceLift() {
        return this.liftChooseStrategy.selectLift(elevatorCartControllerList);
    }


}

// if a lift is idle return that

// else if lift is coming in direction return that

//
