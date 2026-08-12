# Parking Lot System

A Java Swing implementation of a low-level design parking lot system. The app lets you create entry and exit gates, add parking managers, add parking spots, and park two-wheeler or four-wheeler vehicles.

## Features

- Swing-based desktop UI
- Separate parking managers for two-wheeler and four-wheeler spots
- Add parking spots dynamically
- Add one entry gate and one exit gate
- Park vehicles through the entry gate
- Show spot status as `FREE` or `OCCUPIED`
- Show ticket number on occupied parking spots
- Refresh parking status from the UI

## Project Structure

```text
parking/
  src/
    Main.java
    controller/
      ParkingLotUIController.java
    enums/
      VehicleType.java
    factory/
      ParkingManagerFactory.java
      ParkingSpotFactory.java
    model/
      EntranceGate/
      ExitGate/
      ParkingSpot/
      ParkingSpotManager/
      Ticket/
      Vehicle/
    services/
      ParkingSpaceService.java
    ui/
      ParkingLotFrame.java
      ParkingManagerPanel.java
      ParkingBoundaryPanel.java
      ParkVehicleDialog.java
      ExitVehicleDialog.java
```

## Requirements

- JDK 8 or later

## Compile

Run from the `lld` folder:

```bash
javac -d out parking/src/**/*.java
```

## Run

Run from the `lld` folder after compiling:

```bash
java -cp out parking.src.Main
```

## Basic Usage

1. Start the app.
2. Click inside the parking boundary to add an entry gate and exit gate.
3. Add a manager for the required vehicle type.
4. Add parking spots under that manager.
5. Click `PARK VEHICLE`, enter vehicle details, and submit.
6. The matching parking spot updates to `OCCUPIED` and displays the ticket number.

## Notes

- Only one entry gate and one exit gate are currently allowed.
- Exit vehicle flow is currently a UI placeholder.
- Ticket numbers are generated in memory by the entrance gate.
