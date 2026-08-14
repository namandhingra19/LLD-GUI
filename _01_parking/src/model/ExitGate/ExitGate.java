package _01_parking.src.model.ExitGate;

import _01_parking.src.services.ParkingSpaceService;
import _01_parking.src.model.ParkingSpot.ParkingSpot;
import _01_parking.src.model.Ticket.Ticket;

import _01_parking.src.factory.CostComputationFactory;

public class ExitGate {
    private ParkingSpaceService parkingSpaceService;


    public ExitGate(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    private void freeParkingSpot(ParkingSpot parkingSpot){
        parkingSpot.vacate();
    }

    public double computeCost(int ticketId){
        Ticket ticket = this.parkingSpaceService.findTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }
        return CostComputationFactory.getCostComputation(ticket).computeCost(ticket);
    }

    public void unparkVehicle(
        int ticketId
    ){
        Ticket ticket = this.parkingSpaceService.findTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }

        ParkingSpot parkingSpot = ticket.getParkingSpot();
        if (parkingSpot == null || !parkingSpot.isOccupied()) {
            throw new IllegalStateException("Vehicle is already exited");
        }

        freeParkingSpot(parkingSpot);
        this.parkingSpaceService.removeTicket(ticket);
    }
}
