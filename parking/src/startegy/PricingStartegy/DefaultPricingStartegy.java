package parking.src.startegy.PricingStartegy;

import parking.src.model.Ticket.Ticket;

public class DefaultPricingStartegy implements PricingStartegy {
    
    @Override
    public double price(Ticket ticket) {
       return ticket.getParkingSpot().getPrice();
    }
    
}
