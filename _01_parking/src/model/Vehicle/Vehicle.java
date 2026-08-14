package _01_parking.src.model.Vehicle;

import _01_parking.src.enums.VehicleType;

public class Vehicle {
    private String vehicleNo;
    private VehicleType vehicleType;
    
    public Vehicle(String vehicleNo,VehicleType vehicleType){
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNo() {
        return this.vehicleNo;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }
}
