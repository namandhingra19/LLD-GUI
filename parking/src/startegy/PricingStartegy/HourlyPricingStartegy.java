package parking.src.startegy.PricingStartegy;

import parking.src.model.Ticket.Ticket;

public class HourlyPricingStartegy implements PricingStartegy {
    
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
