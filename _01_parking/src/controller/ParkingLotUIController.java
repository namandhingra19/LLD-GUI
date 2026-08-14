package _01_parking.src.controller;

import _01_parking.src.model.EntranceGate.EntranceGate;
import _01_parking.src.model.ExitGate.ExitGate;
import _01_parking.src.model.ParkingSpot.ParkingSpot;
import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.ParkingSpotManager.ParkingSpotManager;
import _01_parking.src.services.ParkingSpaceService;
import _01_parking.src.factory.ParkingSpotFactory;

import java.util.List;


public class ParkingLotUIController {
    private static ParkingLotUIController instance;

    public static ParkingLotUIController getInstance() {
        if (instance == null) {
            instance = new ParkingLotUIController();
        }

        return instance;
    }

    private ParkingSpaceService parkingSpaceService;

    private EntranceGate entranceGate;
    private ExitGate exitGate;



    public ParkingLotUIController() {
        this.parkingSpaceService = new ParkingSpaceService();
    }

    public ParkingSpotManager addManager(
            VehicleType vehicleType
    ) {
        return this.parkingSpaceService.addManager(vehicleType);
    }

    public List<ParkingSpotManager> getManagers(
            VehicleType vehicleType
    ) {
        return this.parkingSpaceService.getManagerFromVehicleType(vehicleType);
    }

    public ParkingSpot addParkingSpot(
            VehicleType vehicleType,
            int managerId
    ) {
        ParkingSpotManager manager =
                this.parkingSpaceService.findManager(vehicleType, managerId);

        if (manager == null) {
            throw new IllegalArgumentException(
                    "Parking manager not found"
            );   
        }

        ParkingSpot spot = ParkingSpotFactory.getParkingSpot(
                vehicleType,
                manager.getSpots().size() + 1
        );

        manager.addParkingSpace(spot);

        return spot;
    }

    public void addEntryGate() {

        if (entranceGate != null) {
            throw new IllegalStateException(
                    "Only one entry gate is allowed"
            );
        }

        entranceGate = new EntranceGate(parkingSpaceService);
    }

    public void addExitGate() {

        if (exitGate != null) {
            throw new IllegalStateException(
                    "Only one exit gate is allowed"
            );
        }

        exitGate = new ExitGate(parkingSpaceService);
    }

    public boolean hasEntryGate() {
        return entranceGate != null;
    }

    public boolean hasExitGate() {
        return exitGate != null;
    }

    public ParkingSpotManager getManager(
        VehicleType vehicleType,
        int managerId
    ) {

        return this.parkingSpaceService.findManager(
                vehicleType,
                managerId
        );
    }

    public void parkVehicle(
        VehicleType vehicleType,
        String vehicleNumber
    ){
        if(entranceGate == null){
            throw new IllegalStateException("No entrance gate available");
        }

        entranceGate.parkVehicle(vehicleType, vehicleNumber);
    }

    public double computeCost(
        int ticketId
    ){
        if(exitGate == null){
            throw new IllegalStateException("No exit gate available");
        }

        return exitGate.computeCost(ticketId);
    }
    
    public void unparkVehicle(
        int ticketId
    ){
        if(exitGate == null){
            throw new IllegalStateException("No exit gate available");
        }

        exitGate.unparkVehicle(ticketId);
    }

}