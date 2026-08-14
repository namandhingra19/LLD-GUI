package _01_parking.src.strategy.PricingStrategy;

import _01_parking.src.model.Ticket.Ticket;

public class MinutePricingStrategy implements PricingStrategy {
    
    @Override
    public double price(Ticket ticket) {
        long durationInMillis = System.currentTimeMillis() - ticket.getEntryTime();
        long durationInMinutes = durationInMillis / (1000 * 60);
        return durationInMinutes * ticket.getParkingSpot().getPrice();
    }
    
}
