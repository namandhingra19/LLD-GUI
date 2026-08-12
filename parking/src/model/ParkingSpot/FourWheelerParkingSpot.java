package parking.src.model.ParkingSpot;

import parking.src.constants.Constants;

public class FourWheelerParkingSpot extends ParkingSpot {
    public FourWheelerParkingSpot(int parkingId){
        super(parkingId, Constants.FOUR_WHEELER_PRICE);
    }
}
