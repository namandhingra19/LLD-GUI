package parking.src.model.ParkingSpot;

import parking.src.model.Ticket.Ticket;
import parking.src.model.Vehicle.Vehicle;

public class ParkingSpot {
    private int parkingId;
    private boolean isOccupied;
    private int price;
    private Ticket ticket;


    public ParkingSpot(int parkingId, int price){
        this.parkingId = parkingId;
        this.price = price;
    }

    public void occupy(Ticket ticket){
        this.isOccupied = true;
        this.ticket = ticket;
    }

    public void vacate(){
        this.isOccupied = false;
        this.ticket = null;
    }

    public int getParkingId() {
        return parkingId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Ticket getTicket() {
        return ticket;
    }

}
