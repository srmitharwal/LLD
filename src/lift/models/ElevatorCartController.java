package lift.models;

import java.util.*;

public class ElevatorCartController {
    ElevatorCart elevatorCart;

    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;

    private List<Integer> waitingList;
    public ElevatorCartController (ElevatorCart elevatorCart) {
        this.elevatorCart = elevatorCart;
        upQueue = new PriorityQueue<>();
        downQueue = new PriorityQueue<>(Collections.reverseOrder());
        waitingList = new ArrayList<>();
    }

    public void acceptRequest(int floorId, Direction direction) {

        if(floorId == elevatorCart.getDisplay().getFloor()) {
            //internalInput();
            return;
        }
        if (elevatorCart.getState().equals(State.IDLE)) {
            if(floorId > elevatorCart.getDisplay().getFloor()) {
                upQueue.add(floorId);
            } else {
                downQueue.add(floorId);
            }

            processRequest();
        } else if (elevatorCart.getDirection().equals(Direction.UP)) {

            if (direction.equals(Direction.UP)) {
                if ( floorId > elevatorCart.getDisplay().getFloor()) {
                    upQueue.add(floorId);
                } else {
                    waitingList.add(floorId);
                }
            } else {
                downQueue.add(floorId);
            }
        } else {
            if (direction.equals(Direction.DOWN)) {
                if ( floorId < elevatorCart.getDisplay().getFloor()) {
                    downQueue.add(floorId);
                } else {
                    waitingList.add(floorId);
                }
            } else {
                upQueue.add(floorId);
            }
        }
        return;
    }

    public void processRequest() {

        if (elevatorCart.getState().equals(State.IDLE)) {
            if (!upQueue.isEmpty()) {
                elevatorCart.setDirection(Direction.UP);
                elevatorCart.setState(State.MOVING);
                processUpRequest();
            } else if (!downQueue.isEmpty()) {
                elevatorCart.setDirection(Direction.DOWN);
                elevatorCart.setState(State.MOVING);
                processDownRequest();
            }
        }
    }

    private void processUpRequest() {

        processQueue(upQueue);
        if (upQueue.isEmpty()) {
            if (waitingList.size() > 0 ) upQueue.addAll(waitingList);
            waitingList = new ArrayList<>();
        }

        if( !downQueue.isEmpty()){
            elevatorCart.setDirection(Direction.DOWN);
            elevatorCart.setState(State.MOVING);
            processDownRequest();
        } else {
            elevatorCart.setState(State.IDLE);
            elevatorCart.setDirection(Direction.IDLE);
        }
    }

    private void processDownRequest() {
        processQueue(downQueue);

        if (downQueue.isEmpty()) {
            if (waitingList.size() > 0 ) downQueue.addAll(waitingList);
            waitingList = new ArrayList<>();
        }

        if( !upQueue.isEmpty()){
            elevatorCart.setDirection(Direction.UP);
            elevatorCart.setState(State.MOVING);
            processUpRequest();

        } else {
            elevatorCart.setState(State.IDLE);
            elevatorCart.setDirection(Direction.IDLE);
        }

    }


    private void processQueue(PriorityQueue<Integer> upQueue) {
        while ( !upQueue.isEmpty()) {
            int floorId = upQueue.poll();
            System.out.println("Elevator with id: " + elevatorCart.getId() + " moving to floor :" + floorId + " Direction: " + elevatorCart.getDirection().toString());

            try {
                Thread.sleep(2*1000);
                elevatorCart.getDisplay().setFloor(floorId);
                System.out.println("Elevator: " + elevatorCart.getId()+" reached at floor :" + floorId);

                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //internalInput();
        }
    }

//    private void internalInput() {
//        System.out.println("do you want to move to go to some other floor, press 1 for yes");
//        Scanner scanner = new Scanner(System.in);
//        int flag = scanner.nextInt();
//        if(flag == 1) {
//            System.out.println("Enter floor where you want to go");
//            int newFloorId = scanner.nextInt();
//            if (newFloorId > elevatorCart.getDisplay().getFloor()) {
//                acceptRequest(newFloorId, Direction.UP);
//            } else {
//                acceptRequest(newFloorId, Direction.DOWN);
//            }
//        }
//    }


    public ElevatorCart getElevatorCart() {
        return elevatorCart;
    }
}
