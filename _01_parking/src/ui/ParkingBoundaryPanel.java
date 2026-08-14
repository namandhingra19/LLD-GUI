package _01_parking.src.ui;

import _01_parking.src.controller.ParkingLotUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParkingBoundaryPanel extends JPanel {

    private final ParkingLotUIController controller;

    private final JLayeredPane layeredPane;

    private final JPanel contentPanel;

    private JLabel entryGate;
    private JLabel exitGate;

    private Point entryPosition;
    private Point exitPosition;

    public ParkingBoundaryPanel(
            ParkingLotUIController controller
    ) {

        this.controller = controller;

        setLayout(new BorderLayout());

        setBorder(
                BorderFactory.createLineBorder(
                        Color.DARK_GRAY,
                        4
                )
        );

        layeredPane =
                new JLayeredPane();

        contentPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        contentPanel.setOpaque(false);

        layeredPane.add(
                contentPanel,
                JLayeredPane.DEFAULT_LAYER
        );

        add(
                layeredPane,
                BorderLayout.CENTER
        );

        // Listen for resize
        layeredPane.addComponentListener(
                new ComponentAdapter() {

                    @Override
                    public void componentResized(
                            ComponentEvent e
                    ) {

                        updateContentBounds();

                        updateGatePositions();
                    }
                }
        );

        // Boundary click
        layeredPane.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        handleBoundaryClick(
                                e.getPoint()
                        );
                    }
                }
        );
    }

    // =========================================================
    // CONTENT
    // =========================================================

    public JPanel getContentPanel() {

        return contentPanel;
    }

    private void updateContentBounds() {

        contentPanel.setBounds(
                0,
                0,
                layeredPane.getWidth(),
                layeredPane.getHeight()
        );
    }

    // =========================================================
    // BOUNDARY CLICK
    // =========================================================

    private void handleBoundaryClick(
            Point point
    ) {

        if (!isOnBoundary(point)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please click on the parking lot boundary.",
                    "Invalid Position",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String[] options = {
                "Entry Gate",
                "Exit Gate",
                "Cancel"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        "What would you like to add here?",
                        "Add Gate",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == 0) {

            addEntryGate(point);

        } else if (choice == 1) {

            addExitGate(point);
        }
    }

    // =========================================================
    // BOUNDARY CHECK
    // =========================================================

    private boolean isOnBoundary(
            Point point
    ) {

        int margin = 30;

        return point.x <= margin
                || point.x >= layeredPane.getWidth() - margin
                || point.y <= margin
                || point.y >= layeredPane.getHeight() - margin;
    }

    // =========================================================
    // ENTRY
    // =========================================================

    private void addEntryGate(
            Point point
    ) {

        if (controller.hasEntryGate()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Only one Entry Gate is allowed."
            );

            return;
        }

        controller.addEntryGate();

        entryPosition = point;

        entryGate =
                createGateLabel(
                        "ENTRY GATE"
                );

        layeredPane.add(
                entryGate,
                JLayeredPane.PALETTE_LAYER
        );

        updateGatePositions();

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    // =========================================================
    // EXIT
    // =========================================================

    private void addExitGate(
            Point point
    ) {

        if (controller.hasExitGate()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Only one Exit Gate is allowed."
            );

            return;
        }

        controller.addExitGate();

        exitPosition = point;

        exitGate =
                createGateLabel(
                        "EXIT GATE"
                );

        layeredPane.add(
                exitGate,
                JLayeredPane.PALETTE_LAYER
        );

        updateGatePositions();

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    // =========================================================
    // GATE COMPONENT
    // =========================================================

    private JLabel createGateLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.CENTER
                );

        label.setOpaque(true);

        label.setBackground(
                new Color(220, 240, 220)
        );

        label.setBorder(
                BorderFactory.createLineBorder(
                        Color.DARK_GRAY,
                        2
                )
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        return label;
    }

    // =========================================================
    // POSITION GATES
    // =========================================================

    private void updateGatePositions() {

        if (entryGate != null
                && entryPosition != null) {

            positionGate(
                    entryGate,
                    entryPosition
            );
        }

        if (exitGate != null
                && exitPosition != null) {

            positionGate(
                    exitGate,
                    exitPosition
            );
        }
    }

    private void positionGate(
            JLabel gate,
            Point position
    ) {

        int width = 110;
        int height = 40;

        gate.setBounds(
                position.x - width / 2,
                position.y - height / 2,
                width,
                height
        );
    }
}