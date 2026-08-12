package parking.src.startegy.PricingStartegy;

import parking.src.model.Ticket.Ticket;

public class MinutePricingStartegy implements PricingStartegy {
    
    @Override
    public double price(Ticket ticket) {
        long durationInMillis = System.currentTimeMillis() - ticket.getEntryTime();
        long durationInMinutes = durationInMillis / (1000 * 60);
        return durationInMinutes * ticket.getParkingSpot().getPrice();
    }
    
}
