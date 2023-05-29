package lift.strategy;

import lift.models.ElevatorCartController;

import java.util.List;
import java.util.Random;

public class DefaultLiftChooseStrategy implements LiftChooseStrategy{


    @Override
    public ElevatorCartController selectLift(List<ElevatorCartController> elevatorCartControllerList) {

//        for (ElevatorCartController elevatorCartController :  elevatorCartControllerList) {
//            if (elevatorCartController.getElevatorCart().getState().equals(State.IDLE)) {
//                return elevatorCartController;
//            }
//        }

        Random random = new Random();
        int liftNo = random.nextInt(elevatorCartControllerList.size());
        return  elevatorCartControllerList.get(liftNo);
    }
}
