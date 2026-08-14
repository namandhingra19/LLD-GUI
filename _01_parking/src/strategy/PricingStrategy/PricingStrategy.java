package _01_parking.src.strategy.PricingStrategy;

import _01_parking.src.model.Ticket.Ticket;

public interface PricingStrategy {
    double price(Ticket ticket);
}
