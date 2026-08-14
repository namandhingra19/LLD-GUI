package _01_parking.src.model.CostComputation;

import _01_parking.src.model.Ticket.Ticket;
import _01_parking.src.strategy.PricingStrategy.PricingStrategy;

public class CostComputation {
    protected PricingStrategy pricingStartegy;

    public CostComputation(PricingStrategy pricingStartegy) {
        this.pricingStartegy = pricingStartegy;
    }

    public double computeCost(Ticket ticket) {
        return pricingStartegy.price(ticket);
    }

}
