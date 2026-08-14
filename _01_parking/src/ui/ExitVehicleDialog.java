
package _01_parking.src.ui;

import javax.swing.*;

import _01_parking.src.controller.ParkingLotUIController;

import java.awt.*;

public class ExitVehicleDialog extends JDialog {

    private JTextField ticketIdField;

    private JComboBox<String> paymentMethodComboBox;
    private final ParkingLotUIController controller = ParkingLotUIController.getInstance();
    private final Runnable refreshSpots;

    public ExitVehicleDialog(JFrame parent) {

        this(parent, null);
    }

    public ExitVehicleDialog(
            JFrame parent,
            Runnable refreshSpots
    ) {

        super(parent, "Exit Vehicle", true);
        this.refreshSpots = refreshSpots;
        setSize(500, 350);
        setLocationRelativeTo(parent);
        

        initializeUI();
    }

    private void initializeUI() {

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel ticketLabel =
                new JLabel("Ticket ID:");

        ticketIdField =
                new JTextField(20);

        JButton calculateButton =
                new JButton("Calculate Cost");

        JLabel paymentLabel =
                new JLabel("Payment Method:");

        paymentMethodComboBox =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "Card",
                                "UPI"
                        }
                );

        JButton payButton =
                new JButton("PAY & EXIT");

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(ticketLabel, gbc);

        gbc.gridx = 1;

        panel.add(ticketIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        panel.add(calculateButton, gbc);

        gbc.gridy = 2;

        panel.add(paymentLabel, gbc);

        gbc.gridy = 3;

        panel.add(paymentMethodComboBox, gbc);

        gbc.gridy = 4;

        panel.add(payButton, gbc);

        calculateButton.addActionListener(
                e -> calculateCost()
        );

        payButton.addActionListener(
                e -> makePayment()
        );

        add(panel);
    }

    private void calculateCost() {

        String ticketId =
                ticketIdField.getText().trim();

        if (ticketId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter ticket ID"
            );

            return;
        }

        try {

            double cost =
                    controller.computeCost(
                            parseTicketId(ticketId)
                    );

            JOptionPane.showMessageDialog(
                        this,
                        "Cost for ticket ID " + ticketId + ": " + cost
                );

        } catch (Exception ex) {

            showError(ex.getMessage());
        }
        /*
         * Your LLD:
         *
         * Ticket ticket = ticketRepository.getTicket(ticketId);
         *
         * CostComputation computation =
         *      costComputationFactory
         *          .getCostComputation(ticket);
         *
         * double amount =
         *      computation.price(ticket);
         */

        
    }

    private void makePayment() {

        String ticketId =
                ticketIdField.getText().trim();

        if (ticketId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter ticket ID"
            );

            return;
        }

        String paymentMethod =
                (String) paymentMethodComboBox
                        .getSelectedItem();

        /*
         * Your LLD:
         *
         * Payment payment =
         *      paymentFactory
         *          .getPayment(paymentMethod);
         *
         * payment.makePayment(amount);
         *
         * exitGate.removeVehicle(ticket);
         */

        try {

            controller.unparkVehicle(
                    parseTicketId(ticketId)
            );

            if (refreshSpots != null) {
                refreshSpots.run();
            }
        
            JOptionPane.showMessageDialog(
                    this,
                    "Payment successful using "
                            + paymentMethod
            );

            dispose();

        } catch (Exception ex) {

            showError(ex.getMessage());
        }
    }

    private int parseTicketId(
            String ticketId
    ) {

        try {

            return Integer.parseInt(ticketId);

        } catch (NumberFormatException ex) {

            throw new IllegalArgumentException(
                    "Ticket ID must be a number"
            );
        }
    }

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
