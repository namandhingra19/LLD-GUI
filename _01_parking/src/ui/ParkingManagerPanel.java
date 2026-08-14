package _01_parking.src.ui;

import _01_parking.src.controller.ParkingLotUIController;
import _01_parking.src.model.ParkingSpot.ParkingSpot;
import _01_parking.src.model.ParkingSpotManager.ParkingSpotManager;
import _01_parking.src.enums.VehicleType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParkingManagerPanel extends JPanel {

    private final VehicleType vehicleType;
    private final int managerId;

    private final ParkingLotUIController controller;

    private final JPanel spotsPanel;

    public ParkingManagerPanel(
            VehicleType vehicleType,
            int managerId,
            ParkingLotUIController controller
    ) {

        this.vehicleType = vehicleType;
        this.managerId = managerId;
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.GRAY
                        ),
                        new EmptyBorder(10, 10, 10, 10)
                )
        );

        // Header
        JPanel header = new JPanel(
                new BorderLayout()
        );

        JLabel managerLabel = new JLabel(
                "Manager #" + managerId
        );

        managerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        JButton addSpotButton =
                new JButton("+ ADD PARKING SPOT");

        addSpotButton.addActionListener(
                e -> addParkingSpot()
        );

        header.add(
                managerLabel,
                BorderLayout.WEST
        );

        header.add(
                addSpotButton,
                BorderLayout.EAST
        );

        add(header, BorderLayout.NORTH);

        // Spots
        spotsPanel = new JPanel(
                new GridLayout(0, 5, 10, 10)
        );

        add(
                spotsPanel,
                BorderLayout.CENTER
        );
    }
    public void refreshSpots() {

        spotsPanel.removeAll();

        
        ParkingSpotManager manager =
                controller.getManager(
                        vehicleType,
                        managerId
                );
        
        for (ParkingSpot spot :
                manager.getSpots()) {

            addSpotCard(spot);
        }

        spotsPanel.revalidate();
        spotsPanel.repaint();
    }

    private void addParkingSpot() {
 try {

            controller.addParkingSpot(
                    vehicleType,
                    managerId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Parking spot created successfully!"
            );


        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    // dialog.setVisible(true);

    refreshSpots();
}

    // private void addParkingSpot() {

    //     try {

    //         ParkingSpot spot =
    //                 controller.addParkingSpot(
    //                         vehicleType,
    //                         managerId
    //                 );

    //         /*
    //          * The controller will eventually return
    //          * the actual ParkingSpot from your LLD.
    //          */

    //         if (spot != null) {

    //             addSpotCard(spot);

    //             revalidate();
    //             repaint();
    //         }

    //     } catch (Exception ex) {

    //         JOptionPane.showMessageDialog(
    //                 this,
    //                 ex.getMessage(),
    //                 "Error",
    //                 JOptionPane.ERROR_MESSAGE
    //         );
    //     }
    // }

    private void addSpotCard(
            ParkingSpot spot
    ) {

        JPanel card = new JPanel(
                new GridLayout(4, 1)
        );

        card.setPreferredSize(
                new Dimension(150, 110)
        );

        card.setBackground(
                getCardBackground(spot)
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        getCardBorderColor(spot)
                )
        );

        JLabel idLabel = new JLabel(
                String.valueOf(
                        spot.getParkingId()
                ),
                SwingConstants.CENTER
        );

        JLabel statusLabel = new JLabel(
                spot.isOccupied()
                        ? "OCCUPIED"
                        : "FREE",
                SwingConstants.CENTER
        );

        JLabel ticketLabel = new JLabel(
                getTicketText(spot),
                SwingConstants.CENTER
        );

        JLabel vehicleLabel = new JLabel(
                getVehicleText(spot),
                SwingConstants.CENTER
        );

        vehicleLabel.setToolTipText(
                getVehicleText(spot)
        );

        idLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        vehicleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        updateStatusColor(
                statusLabel,
                spot
        );

        card.add(idLabel);
        card.add(statusLabel);
        card.add(ticketLabel);
        card.add(vehicleLabel);

        spotsPanel.add(card);
    }

    private String getTicketText(
            ParkingSpot spot
    ) {

        if (!spot.isOccupied()
                || spot.getTicket() == null) {

            return "Ticket: -";
        }

        return "Ticket: "
                + spot.getTicket().getTicketNo();
    }

    private String getVehicleText(
            ParkingSpot spot
    ) {

        if (!spot.isOccupied()
                || spot.getTicket() == null
                || spot.getTicket().getVehicle() == null) {

            return "Vehicle: -";
        }

        return getVehicleIcon(spot)
                + spot.getTicket()
                        .getVehicle()
                        .getVehicleNo();
    }

    private String getVehicleIcon(
            ParkingSpot spot
    ) {

        if (spot.getTicket()
                .getVehicle()
                .getVehicleType()
                == VehicleType.TWO_WHEELER) {

            return "\uD83C\uDFCD\uFE0F ";
        }

        return "\uD83D\uDE97 ";
    }

    private void updateStatusColor(
            JLabel label,
            ParkingSpot spot
    ) {

        if (spot.isOccupied()) {

            label.setForeground(Color.RED);

        } else {

            label.setForeground(
                    new Color(0, 140, 0)
            );
        }
    }

    private Color getCardBackground(
            ParkingSpot spot
    ) {

        if (spot.isOccupied()) {

            return new Color(255, 235, 235);
        }

        return new Color(235, 255, 235);
    }

    private Color getCardBorderColor(
            ParkingSpot spot
    ) {

        if (spot.isOccupied()) {

            return Color.RED;
        }

        return new Color(0, 140, 0);
    }
}
