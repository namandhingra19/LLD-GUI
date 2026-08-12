package parking.src.model.ParkingSpotManager;

import java.util.List;

import parking.src.model.ParkingSpot.ParkingSpot;

public class ParkingSpotManager {
    int managerId;
    List<ParkingSpot>list;

    public ParkingSpotManager(int mangerId,List<ParkingSpot>list){
        this.managerId = mangerId;
        this.list = list;
    }

    public int getManagerId() {
        return this.managerId;
    }
    
    public List<ParkingSpot> getSpots() {
        return this.list;
    }

    public ParkingSpot getAvailableSpot(){
        for(ParkingSpot spot: list){
            if(!spot.isOccupied()){
                return spot;
            }
        }
        return null;
    }

    public boolean hasAvailableSpots(){
        for(ParkingSpot spot: list){
            if(!spot.isOccupied()){
                return true;
            }
        }
        return false;
    }

    public void addParkingSpace(ParkingSpot parkingSpot){
        list.add(parkingSpot);
    }

    public void removeParkingSpace(ParkingSpot parkingSpot){
        list.remove(parkingSpot);
    }

    public void parkVehicle(ParkingSpot parkingSpot){

    }

    public void removeVehicle(ParkingSpot parkingSpot){

    }


}
