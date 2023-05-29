package lift.models;

public class ElevatorCart {
    int id;
    Display display;
    Direction direction;
    State state;
    InternalButton internalButton;


    public ElevatorCart(int id, Display display){
        this.id = id;
        this.direction = display.getDirection();
        this.display = display;
        this.state = State.IDLE;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public InternalButton getInternalButton() {
        return internalButton;
    }

    public void setInternalButton(InternalButton internalButton) {
        this.internalButton = internalButton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
