package parking.src.factory;

import parking.src.enums.VehicleType;
import parking.src.model.ParkingSpot.FourWheelerParkingSpot;
import parking.src.model.ParkingSpot.ParkingSpot;
import parking.src.model.ParkingSpot.TwoWheelerParkingSpot;

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
