package lift.models;

public class ExternalButton {
    private ExternalButtonDispatcher externalButtonDispatcher;

    public ExternalButton (ExternalButtonDispatcher externalButtonDispatcher) {
        this.externalButtonDispatcher = externalButtonDispatcher;
    }

    public void pressButton(int floorId, Direction direction) {
        externalButtonDispatcher.submitRequest(floorId, direction);
    }
}
