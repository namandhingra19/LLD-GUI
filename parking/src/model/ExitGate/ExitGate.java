package parking.src.model.ExitGate;

import parking.src.services.ParkingSpaceService;

public class ExitGate {
    private ParkingSpaceService parkingSpaceService;
    public ExitGate(ParkingSpaceService parkingSpaceService){
        this.parkingSpaceService = parkingSpaceService;
    }
}
