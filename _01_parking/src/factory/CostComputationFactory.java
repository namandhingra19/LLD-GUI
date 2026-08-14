package _01_parking.src.factory;

import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.CostComputation.CostComputation;
import _01_parking.src.model.CostComputation.FourWheelerCostComputation;
import _01_parking.src.model.CostComputation.TwoWheelerCostComputation;
import _01_parking.src.model.Ticket.Ticket;
import _01_parking.src.strategy.PricingStrategy.DefaultPricingStrategy;
import _01_parking.src.strategy.PricingStrategy.HourlyPricingStrategy;
import _01_parking.src.strategy.PricingStrategy.MinutePricingStrategy;

public class CostComputationFactory {
    public static CostComputation getCostComputation(
        Ticket ticket
    ){
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        switch(vehicleType){
            case FOUR_WHEELER:
                return new FourWheelerCostComputation(new DefaultPricingStrategy());
            case TWO_WHEELER:
                return new TwoWheelerCostComputation(new MinutePricingStrategy());
            default:
                return null;
        }
    }
}
