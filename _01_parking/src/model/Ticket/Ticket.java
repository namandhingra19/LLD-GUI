package _01_parking.src.model.Ticket;

import _01_parking.src.model.ParkingSpot.ParkingSpot;
import _01_parking.src.model.Vehicle.Vehicle;

public class Ticket {
    private int ticketNo;
    private long entryTime;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;


    public Ticket(int ticketNo, long entryTime, ParkingSpot parkingSpot, Vehicle vehicle){
        this.ticketNo = ticketNo;
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    
}
