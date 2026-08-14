package _01_parking.src.model.ParkingSpotManager;

import java.util.ArrayList;
import java.util.List;

import _01_parking.src.model.ParkingSpot.ParkingSpot;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {
    public FourWheelerParkingSpotManager(int id){
        super(id, new ArrayList<ParkingSpot>());
    }
}
