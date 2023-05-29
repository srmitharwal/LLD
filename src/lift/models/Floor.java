package lift.models;

public class Floor {
    private int floorId;
    private Direction direction;
    private Display display;
    private ExternalButton externalButton;

    public Floor(int floorId, Display display, ExternalButton externalButton) {
        this.display = display;
        this.floorId = floorId;
        this.externalButton = externalButton;
        this.direction = Direction.IDLE;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public ExternalButton getExternalButton() {
        return externalButton;
    }

    public void setExternalButton(ExternalButton externalButton) {
        this.externalButton = externalButton;
    }
}
