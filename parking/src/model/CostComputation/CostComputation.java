package parking.src.model.CostComputation;

import parking.src.model.Ticket.Ticket;
import parking.src.startegy.PricingStartegy.PricingStartegy;

public class CostComputation {
    protected PricingStartegy pricingStartegy;

    public CostComputation(PricingStartegy pricingStartegy) {
        this.pricingStartegy = pricingStartegy;
    }

    public double computeCost(Ticket ticket) {
        return pricingStartegy.price(ticket);
    }

}
