package _01_parking.src.model.EntranceGate;

import _01_parking.src.factory.ParkingManagerFactory;
import _01_parking.src.model.ParkingSpotManager.ParkingSpotManager;
import _01_parking.src.model.Ticket.Ticket;
import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.Vehicle.Vehicle;
import _01_parking.src.services.ParkingSpaceService;
import _01_parking.src.model.ParkingSpot.ParkingSpot;
import java.util.ArrayList;
import java.util.List;


public class EntranceGate {

    private ParkingSpaceService parkingSpaceService;

    public EntranceGate(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    private ParkingSpot findParkingSpace(VehicleType vehicleType){
        ParkingSpotManager manager = this.parkingSpaceService.findParkingSpotManger(vehicleType);
        if(manager == null){
            throw new IllegalArgumentException("No available parking spot");
        }
        return manager.getAvailableSpot();
    }

    private void updateParkingSpot(ParkingSpot parkingSpot, Ticket ticket){
        parkingSpot.occupy(ticket);
    }

    private Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        Ticket ticket = new Ticket(this.parkingSpaceService.getTickets().size() + 1,System.currentTimeMillis(),parkingSpot,vehicle);
        this.parkingSpaceService.addTicket(ticket);
        return ticket;
    }

    public void parkVehicle(
        VehicleType vehicleType,
        String vehicleNumber
    ){
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
        ParkingSpot parkingSpot = this.findParkingSpace(vehicleType);
        Ticket ticket = generateTicket(vehicle, parkingSpot);
        updateParkingSpot(parkingSpot, ticket);

    }

}
