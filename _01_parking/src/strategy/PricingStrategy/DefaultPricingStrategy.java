package _01_parking.src.strategy.PricingStrategy;

import _01_parking.src.model.Ticket.Ticket;

public class DefaultPricingStrategy implements PricingStrategy {
    
    @Override
    public double price(Ticket ticket) {
       return ticket.getParkingSpot().getPrice();
    }
    
}
