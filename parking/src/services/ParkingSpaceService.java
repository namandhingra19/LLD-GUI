package parking.src.services;

import java.util.List;

import parking.src.enums.VehicleType;
import parking.src.factory.ParkingManagerFactory;
import parking.src.model.ParkingSpotManager.ParkingSpotManager;
import parking.src.model.Ticket.Ticket;

import java.util.ArrayList;


public class ParkingSpaceService {
    private final List<ParkingSpotManager>twoWheelerManagers;
    private final List<ParkingSpotManager>fourWheelerManagers;
    private List<Ticket> tickets;


    public ParkingSpaceService() {
        this.twoWheelerManagers = new ArrayList<>();
        this.fourWheelerManagers = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public List<ParkingSpotManager> getManagerFromVehicleType(VehicleType vehicleType){
        if(vehicleType == VehicleType.FOUR_WHEELER){
            return fourWheelerManagers;
        } else if(vehicleType == VehicleType.TWO_WHEELER){
            return twoWheelerManagers;
        } else {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }

    public ParkingSpotManager findManager(
            VehicleType vehicleType,
            int managerId
    ) {
        List<ParkingSpotManager> managers = getManagerFromVehicleType(vehicleType);


        for (ParkingSpotManager manager : managers) {
            if (manager.getManagerId() == managerId) {
                return manager;
            }
        }

        return null;
    }

    public ParkingSpotManager addManager(
            VehicleType vehicleType
    ) {
        List<ParkingSpotManager> managers = getManagerFromVehicleType(vehicleType);
        ParkingSpotManager manager = ParkingManagerFactory.getParkingManager(managers.size() + 1, vehicleType);
        managers.add(manager);
        return manager;
    }

    public ParkingSpotManager findParkingSpotManger(VehicleType vehicleType){
        List<ParkingSpotManager> managers = getManagerFromVehicleType(vehicleType);
        for(ParkingSpotManager manager:managers){
            if(manager.hasAvailableSpots()){
                return manager;
            }
        }
        return null;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    public Ticket findTicket(int ticketId){
        for(Ticket ticket: tickets){
            if(ticket.getTicketNo() == ticketId){
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

}
