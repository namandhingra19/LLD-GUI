package _01_parking.src.factory;

import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.ParkingSpot.FourWheelerParkingSpot;
import _01_parking.src.model.ParkingSpot.ParkingSpot;
import _01_parking.src.model.ParkingSpot.TwoWheelerParkingSpot;

public class ParkingSpotFactory {
    public static ParkingSpot getParkingSpot(
        VehicleType vehicleType,int id
    ){
        switch(vehicleType){
            case FOUR_WHEELER:
                return new FourWheelerParkingSpot(id);
            case TWO_WHEELER:
                return new TwoWheelerParkingSpot(id);
            default:
                return null;
        }
    }
}
