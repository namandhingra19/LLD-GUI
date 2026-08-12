package parking.src.factory;

import parking.src.enums.VehicleType;
import parking.src.model.CostComputation.CostComputation;
import parking.src.model.CostComputation.FourWheelerCostComputation;
import parking.src.model.CostComputation.TwoWheelerCostComputation;
import parking.src.model.Ticket.Ticket;
import parking.src.startegy.PricingStartegy.DefaultPricingStartegy;
import parking.src.startegy.PricingStartegy.HourlyPricingStartegy;
import parking.src.startegy.PricingStartegy.MinutePricingStartegy;

public class CostComputationFactory {
    public static CostComputation getCostComputation(
        Ticket ticket
    ){
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        switch(vehicleType){
            case FOUR_WHEELER:
                return new FourWheelerCostComputation(new DefaultPricingStartegy());
            case TWO_WHEELER:
                return new TwoWheelerCostComputation(new MinutePricingStartegy());
            default:
                return null;
        }
    }
}
