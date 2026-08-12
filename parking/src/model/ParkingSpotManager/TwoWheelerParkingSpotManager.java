package parking.src.model.ParkingSpotManager;

import parking.src.model.ParkingSpot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {
    public static List<ParkingSpot> l;
    public TwoWheelerParkingSpotManager(int id){
        super(id, new ArrayList<ParkingSpot>() );
    }
}
