package parking.src.factory;

import parking.src.enums.VehicleType;
import parking.src.model.ParkingSpotManager.FourWheelerParkingSpotManager;
import parking.src.model.ParkingSpotManager.ParkingSpotManager;
import parking.src.model.ParkingSpotManager.TwoWheelerParkingSpotManager;

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
