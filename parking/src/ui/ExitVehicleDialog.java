
package parking.src.ui;

import javax.swing.*;
import java.awt.*;

public class ExitVehicleDialog extends JDialog {

    private JTextField ticketIdField;

    private JComboBox<String> paymentMethodComboBox;

    public ExitVehicleDialog(JFrame parent) {

        super(parent, "Exit Vehicle", true);

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

        JOptionPane.showMessageDialog(
                this,
                "Cost computation called for:\n"
                        + ticketId
        );
    }

    private void makePayment() {

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

        JOptionPane.showMessageDialog(
                this,
                "Payment successful using "
                        + paymentMethod
        );

        dispose();
    }
}