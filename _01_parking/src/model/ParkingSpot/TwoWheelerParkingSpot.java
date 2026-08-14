package _01_parking.src.model.ParkingSpot;

import _01_parking.src.model.Vehicle.Vehicle;
import _01_parking.src.constants.Constants;

public class TwoWheelerParkingSpot extends ParkingSpot {
    public TwoWheelerParkingSpot(int parkingId){
        super(parkingId, Constants.FOUR_WHEELER_PRICE);
    }
}
