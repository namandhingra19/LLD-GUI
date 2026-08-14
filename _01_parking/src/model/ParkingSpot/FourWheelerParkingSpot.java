package _01_parking.src.model.ParkingSpot;

import _01_parking.src.constants.Constants;

public class FourWheelerParkingSpot extends ParkingSpot {
    public FourWheelerParkingSpot(int parkingId){
        super(parkingId, Constants.FOUR_WHEELER_PRICE);
    }
}
