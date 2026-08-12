package parking.src.startegy.PricingStartegy;

import parking.src.model.Ticket.Ticket;

public interface PricingStartegy {
    double price(Ticket ticket);
}
