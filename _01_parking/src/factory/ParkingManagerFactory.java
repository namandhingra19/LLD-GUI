package _01_parking.src.factory;

import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.ParkingSpotManager.FourWheelerParkingSpotManager;
import _01_parking.src.model.ParkingSpotManager.ParkingSpotManager;
import _01_parking.src.model.ParkingSpotManager.TwoWheelerParkingSpotManager;

public class ParkingManagerFactory {
    public static ParkingSpotManager getParkingManager(int id,VehicleType vehicleType){
        switch(vehicleType){
            case FOUR_WHEELER:
                return new FourWheelerParkingSpotManager(id);
            case TWO_WHEELER:
                return new TwoWheelerParkingSpotManager(id);
            default:
                return null;
        }
    }
}
