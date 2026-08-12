package parking.src.model.Ticket;

import parking.src.model.ParkingSpot.ParkingSpot;
import parking.src.model.Vehicle.Vehicle;

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
}
