package parking.src.ui;

import javax.swing.*;

import parking.src.controller.ParkingLotUIController;
import parking.src.enums.VehicleType;

import java.awt.*;

public class ParkVehicleDialog extends JDialog {

    private JTextField vehicleNumberField;
    private JComboBox<String> vehicleTypeComboBox;
    private final ParkingLotUIController controller;
    private final Runnable refreshSpots;


    public ParkVehicleDialog(JFrame parent) {

        this(parent, null);
    }

    public ParkVehicleDialog(
            JFrame parent,
            Runnable refreshSpots
    ) {

        super(parent, "Park Vehicle", true);
        controller = ParkingLotUIController.getInstance();
        this.refreshSpots = refreshSpots;

        setSize(450, 300);
        setLocationRelativeTo(parent);

        initializeUI();
    }

    private void initializeUI() {

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel vehicleNumberLabel =
                new JLabel("Vehicle Number:");

        vehicleNumberField =
                new JTextField(20);

        JLabel vehicleTypeLabel =
                new JLabel("Vehicle Type:");

        vehicleTypeComboBox =
                new JComboBox<>(
                        new String[]{
                                VehicleType.TWO_WHEELER.toString(),
                                VehicleType.FOUR_WHEELER.toString()
                        }
                );

        JButton findSpotButton =
                new JButton("Find Parking Spot");

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(vehicleNumberLabel, gbc);

        gbc.gridx = 1;

        panel.add(vehicleNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(vehicleTypeLabel, gbc);

        gbc.gridx = 1;

        panel.add(vehicleTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(findSpotButton, gbc);

        findSpotButton.addActionListener(
                e -> findParkingSpot()
        );

        add(panel);
    }

    private void findParkingSpot() {

        String vehicleNumber =
                vehicleNumberField.getText().trim();

        if (vehicleNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter vehicle number"
            );

            return;
        }

        String selectedType =
                (String) vehicleTypeComboBox
                        .getSelectedItem();
        
        JOptionPane.showMessageDialog(
                this,
                "Calling EntranceGate...\n\n" +
                "Vehicle: " + vehicleNumber +
                "\nType: " + selectedType
        );

        controller.parkVehicle(
                VehicleType.valueOf(selectedType),
                vehicleNumber
        );

        if (refreshSpots != null) {
            refreshSpots.run();
        }

        /*
         * THIS IS WHERE YOUR LLD STARTS
         *
         * Vehicle vehicle = new Vehicle(...);
         *
         * ParkingSpot spot =
         *      entranceGate.findParkingSpace(vehicle);
         *
         * Then show confirmation dialog.
         */
    }
}
