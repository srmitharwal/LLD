package com.personal.designPattern.parkinglot;

import com.personal.designPattern.parkinglot.bo.Floor;
import com.personal.designPattern.parkinglot.bo.Ticket;
import com.personal.designPattern.parkinglot.bo.parkingspot.BikeParkingSpot;
import com.personal.designPattern.parkinglot.bo.parkingspot.CarParkingSpot;
import com.personal.designPattern.parkinglot.bo.parkingspot.ParkingSpot;
import com.personal.designPattern.parkinglot.bo.vehicle.Vehicle;
import com.personal.designPattern.parkinglot.factory.FloorSelectionFactory;
import com.personal.designPattern.parkinglot.factory.IFloorSelection;
import com.personal.designPattern.parkinglot.factory.costFactory.CostCalculator;
import com.personal.designPattern.parkinglot.factory.costFactory.CostFactory;

import java.util.*;

public class ParkingManager implements IParkingManager{
   private String id;

   private List<Floor> floors;

   public ParkingManager() {
       this.id = UUID.randomUUID().toString();
   }

    @Override
    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many floors you want to create, please enter");
        int noOfFloors = sc.nextInt();
        floors = new ArrayList<>(noOfFloors);

        for(int i = 1; i <= noOfFloors; i++) {
            Floor floor = new Floor(i);

            System.out.println("How many car spots on floorNo: " + i);
            int noOfCarSpots = sc.nextInt();
            List<ParkingSpot> carParkingSpots = new ArrayList<>(noOfCarSpots);
            for(int j = 1; j <= noOfCarSpots; j++) {
                ParkingSpot carParkingSpot = new CarParkingSpot(j);
                carParkingSpots.add(carParkingSpot);
            }

            System.out.println("How many bike spots on floorNo: " + i);
            int noOfBikeParking = sc.nextInt();
            List<ParkingSpot> bikeParkingSpots = new ArrayList<>(noOfBikeParking);
            for(int j = 1; j <= noOfBikeParking; j++) {
                ParkingSpot bikeParkingSpot = new BikeParkingSpot(j);
                bikeParkingSpots.add(bikeParkingSpot);
            }
            floor.setAvailableBikeParkingSpot(new HashSet<>(bikeParkingSpots));
            floor.setAvailableCarParkingSpot(new HashSet<>(carParkingSpots));
            floors.add(floor);
        }
    }


    @Override
    public Ticket bookParking(Vehicle vehicle) {

        System.out.println("Going to book details for vehicle No: " + vehicle.getVehicleNo() + " type : " +vehicle.getType());

        IFloorSelection floorSelection = FloorSelectionFactory.getInstance().getFloorSelection(vehicle);
        int floorNo = floorSelection.selectFloor(floors);

       if (floorNo == -1 ) {
           System.out.println("no available parking for vehicle: " + vehicle.getVehicleNo());
           return null;
       }

       Floor floor = floors.get(floorNo);
       Ticket ticket = floor.bookSpot(vehicle);

       System.out.println("Spot details for Vehicle: " + vehicle.getVehicleNo() +
               " Parking Floor: " + ticket.getFloorId() + " ParkingSpotNo: " + ticket.getParkingSpot().getId());

       return ticket;
    }

    @Override
    public boolean exitParking(Ticket ticket) {

       System.out.println("Freeing booking spot: " + ticket.getParkingSpot().getId() + " at Floor: " + ticket.getFloorId() + " parking type : " + ticket.getVehicle().getType());
       int floorId = ticket.getFloorId();
       Floor floor = findFloor(floorId);
       floor.freeBookingSpot(ticket.getParkingSpot());
       CostCalculator costCalculator = CostFactory.getInstance().getCostCalculator(ticket.getVehicle());
       int cost = costCalculator.caculateCost(ticket);
       System.out.println("collect money: " + cost);
       return true;
    }

    private Floor findFloor(int floorId) {

       for(Floor floor : floors) {
           if(floor.getId() == floorId)return floor;
       }
       return  null;
    }

    @Override
    public int availableParkingsForBikes() {
        return 0;
    }

    @Override
    public int availableParkingsForCars() {
        return 0;
    }


}
