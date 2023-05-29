package lift.models;

public class InternalButtonDispatcher {
    ElevatorCartController elevatorCartController;

    public InternalButtonDispatcher(ElevatorCartController elevatorCartController) {
        this.elevatorCartController = elevatorCartController;
    }

    public void submitRequest(int floorId, Direction direction) {
        elevatorCartController.acceptRequest(floorId, direction);
    }
}



// 6, 7
//
// 2 ,  up