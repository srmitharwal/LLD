package lift.models;

public class InternalButton {
    InternalButtonDispatcher internalButtonDispatcher;

    public InternalButton(InternalButtonDispatcher internalButtonDispatcher) {
        this.internalButtonDispatcher = internalButtonDispatcher;
    }

    public void pressButton(int floorId) {
        ElevatorCart elevatorCart = internalButtonDispatcher.elevatorCartController.getElevatorCart();

        if (floorId > elevatorCart.getDisplay().getFloor()) {
            internalButtonDispatcher.submitRequest(floorId, Direction.UP);
        } else {
            internalButtonDispatcher.submitRequest(floorId, Direction.DOWN);
        }
    }
}
