package parking.src.model.EntranceGate;

import parking.src.factory.ParkingManagerFactory;
import parking.src.model.ParkingSpotManager.ParkingSpotManager;
import parking.src.model.Ticket.Ticket;
import parking.src.enums.VehicleType;
import parking.src.model.Vehicle.Vehicle;
import parking.src.services.ParkingSpaceService;
import parking.src.model.ParkingSpot.ParkingSpot;
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
