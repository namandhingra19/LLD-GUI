package parking.src.model.ParkingSpotManager;

import java.util.ArrayList;
import java.util.List;

import parking.src.model.ParkingSpot.ParkingSpot;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {
    public FourWheelerParkingSpotManager(int id){
        super(id, new ArrayList<ParkingSpot>());
    }
}
