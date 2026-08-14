package _01_parking.src.ui;

import _01_parking.src.controller.ParkingLotUIController;
import _01_parking.src.enums.VehicleType;
import _01_parking.src.model.ParkingSpotManager.ParkingSpotManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParkingLotFrame extends JFrame {

    private final ParkingLotUIController controller;

    private JPanel twoWheelerManagersPanel;
    private JPanel fourWheelerManagersPanel;

    private JLabel twoWheelerAvailableLabel;
    private JLabel fourWheelerAvailableLabel;

    private ParkingBoundaryPanel boundaryPanel;

    public ParkingLotFrame() {
        controller = ParkingLotUIController.getInstance();
        setTitle("Parking Lot System");
        setSize(1200, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    // =========================================================
    // INITIALIZE UI
    // =========================================================

    private void initializeUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // Header
        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // Parking boundary
        boundaryPanel =
                new ParkingBoundaryPanel(controller);

        /*
         * We want to place our parking managers
         * INSIDE the boundary.
         */
       JPanel parkingContent =
        createParkingContent();

boundaryPanel
        .getContentPanel()
        .add(
                parkingContent,
                BorderLayout.CENTER
        );
        JScrollPane scrollPane =
                new JScrollPane(boundaryPanel);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Bottom buttons
        mainPanel.add(
                createBottomPanel(),
                BorderLayout.SOUTH
        );

        setContentPane(mainPanel);

        updateStats();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel panel =
                new JPanel(new BorderLayout());

        JLabel title =
                new JLabel("PARKING LOT SYSTEM");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        twoWheelerAvailableLabel =
                new JLabel();

        fourWheelerAvailableLabel =
                new JLabel();

        JPanel stats =
                new JPanel(
                        new GridLayout(2, 1)
                );

        stats.add(
                twoWheelerAvailableLabel
        );

        stats.add(
                fourWheelerAvailableLabel
        );

        panel.add(
                title,
                BorderLayout.WEST
        );

        panel.add(
                stats,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // PARKING CONTENT
    // =========================================================

    private JPanel createParkingContent() {

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        // -----------------------------------------------------
        // TWO WHEELER
        // -----------------------------------------------------

        content.add(
                createManagerHeader(
                        "TWO WHEELER PARKING",
                        VehicleType.TWO_WHEELER
                )
        );

        twoWheelerManagersPanel =
                new JPanel();

        twoWheelerManagersPanel.setLayout(
                new BoxLayout(
                        twoWheelerManagersPanel,
                        BoxLayout.Y_AXIS
                )
        );

        content.add(
                twoWheelerManagersPanel
        );

        content.add(
                Box.createVerticalStrut(25)
        );

        // -----------------------------------------------------
        // FOUR WHEELER
        // -----------------------------------------------------

        content.add(
                createManagerHeader(
                        "FOUR WHEELER PARKING",
                        VehicleType.FOUR_WHEELER
                )
        );

        fourWheelerManagersPanel =
                new JPanel();

        fourWheelerManagersPanel.setLayout(
                new BoxLayout(
                        fourWheelerManagersPanel,
                        BoxLayout.Y_AXIS
                )
        );

        content.add(
                fourWheelerManagersPanel
        );

        return content;
    }

    // =========================================================
    // MANAGER HEADER
    // =========================================================

    private JPanel createManagerHeader(
            String title,
            VehicleType vehicleType
    ) {

        JPanel panel =
                new JPanel(new BorderLayout());

        JLabel label =
                new JLabel(title);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        JButton addManagerButton =
                new JButton("+ ADD MANAGER");

        addManagerButton.addActionListener(
                e -> addManager(vehicleType)
        );

        panel.add(
                label,
                BorderLayout.WEST
        );

        panel.add(
                addManagerButton,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // ADD MANAGER
    // =========================================================

    private void addManager(
            VehicleType vehicleType
    ) {

        try {

            ParkingSpotManager
                    manager =
                    controller.addManager(
                            vehicleType
                    );

            ParkingManagerPanel managerPanel =
                    new ParkingManagerPanel(
                            vehicleType,
                            manager.getManagerId(),
                            controller
                    );

            if (vehicleType ==
                    VehicleType.TWO_WHEELER) {

                twoWheelerManagersPanel.add(
                        managerPanel
                );

                twoWheelerManagersPanel.add(
                        Box.createVerticalStrut(10)
                );

                twoWheelerManagersPanel.revalidate();
                twoWheelerManagersPanel.repaint();

            } else {

                fourWheelerManagersPanel.add(
                        managerPanel
                );

                fourWheelerManagersPanel.add(
                        Box.createVerticalStrut(10)
                );

                fourWheelerManagersPanel.revalidate();
                fourWheelerManagersPanel.repaint();
            }

            updateStats();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BOTTOM BUTTONS
    // =========================================================

    private JPanel createBottomPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );

        JButton parkButton =
                new JButton("PARK VEHICLE");

        JButton exitButton =
                new JButton("EXIT VEHICLE");

        JButton refreshButton =
                new JButton("REFRESH");

        parkButton.setPreferredSize(
                new Dimension(180, 45)
        );

        exitButton.setPreferredSize(
                new Dimension(180, 45)
        );

        refreshButton.setPreferredSize(
                new Dimension(120, 45)
        );

        parkButton.addActionListener(
                e -> openParkDialog()
        );

        exitButton.addActionListener(
                e -> openExitDialog()
        );

        refreshButton.addActionListener(
                e -> refreshParkingStatus()
        );

        panel.add(parkButton);
        panel.add(exitButton);
        panel.add(refreshButton);

        return panel;
    }

    // =========================================================
    // REFRESH
    // =========================================================

    private void refreshParkingStatus() {

        /*
         * Managers are already represented by their own
         * ParkingManagerPanel.
         *
         * Refreshing currently means:
         *
         * 1. Update statistics
         * 2. Repaint the parking UI
         *
         * Later we can make ParkingManagerPanel refresh
         * individual parking spots from the actual LLD.
         */

        updateStats();

        refreshManagerPanels(twoWheelerManagersPanel);
        refreshManagerPanels(fourWheelerManagersPanel);

        boundaryPanel.revalidate();
        boundaryPanel.repaint();
    }

    private void refreshManagerPanels(
            JPanel managersPanel
    ) {

        for (Component component :
                managersPanel.getComponents()) {

            if (component instanceof ParkingManagerPanel) {

                ((ParkingManagerPanel) component)
                        .refreshSpots();
            }
        }

        managersPanel.revalidate();
        managersPanel.repaint();
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStats() {

        long twoWheelerAvailable =
                controller
                        .getManagers(
                                VehicleType.TWO_WHEELER
                        )
                        .stream()
                        .flatMap(
                                manager ->
                                        manager.getSpots()
                                                .stream()
                        )
                        .filter(
                                spot -> !spot.isOccupied()
                        )
                        .count();

        long fourWheelerAvailable =
                controller
                        .getManagers(
                                VehicleType.FOUR_WHEELER
                        )
                        .stream()
                        .flatMap(
                                manager ->
                                        manager.getSpots()
                                                .stream()
                        )
                        .filter(
                                spot -> !spot.isOccupied()
                        )
                        .count();

        twoWheelerAvailableLabel.setText(
                "2-Wheeler Available: "
                        + twoWheelerAvailable
        );

        fourWheelerAvailableLabel.setText(
                "4-Wheeler Available: "
                        + fourWheelerAvailable
        );
    }

    // =========================================================
    // PARK VEHICLE
    // =========================================================

    private void openParkDialog() {

        ParkVehicleDialog dialog =
                new ParkVehicleDialog(
                        this,
                        this::refreshParkingStatus
                );

        dialog.setVisible(true);
    }

    // =========================================================
    // EXIT VEHICLE
    // =========================================================

    private void openExitDialog() {

        ExitVehicleDialog dialog =
                new ExitVehicleDialog(
                        this,
                        this::refreshParkingStatus
                );

        dialog.setVisible(true);
    }
}
