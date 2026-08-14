package _01_parking.src.strategy.PricingStrategy;

import _01_parking.src.model.Ticket.Ticket;

public class HourlyPricingStrategy implements PricingStrategy {
    
    @Override
    public double price(Ticket ticket) {
        long currentTime = System.currentTimeMillis();
        long durationInMillis = currentTime - ticket.getEntryTime();
        long durationInHours = durationInMillis / (1000 * 60 * 60);
        if (durationInHours == 0) {
            durationInHours = 1; 
        }
        return ticket.getParkingSpot().getPrice() * durationInHours;
    }
}
