package lift.strategy;

import lift.models.ElevatorCartController;

import java.util.List;

public interface LiftChooseStrategy {

    public ElevatorCartController selectLift(List<ElevatorCartController> elevatorCartControllerList);
}
