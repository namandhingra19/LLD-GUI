package parking.src.model.ParkingSpot;

import parking.src.model.Vehicle.Vehicle;
import parking.src.constants.Constants;

public class TwoWheelerParkingSpot extends ParkingSpot {
    public TwoWheelerParkingSpot(int parkingId){
        super(parkingId, Constants.FOUR_WHEELER_PRICE);
    }
}
