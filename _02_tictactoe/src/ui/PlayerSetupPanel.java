package _02_tictactoe.src.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import _02_tictactoe.src.controller.UIController;

import java.awt.*;

public class PlayerSetupPanel extends JPanel {

    private final JTextField firstPlayerNameField;
    private final JTextField secondPlayerNameField;
    private final JTextField boardSizeField;
    private final PlayerSetupListener listener;
    private final UIController controller;

    public PlayerSetupPanel(
            PlayerSetupListener listener,
            UIController uiController
    ) {
        this.controller = uiController;
        this.listener = listener;
        this.firstPlayerNameField =
                new JTextField(20);
        this.secondPlayerNameField =
                new JTextField(20);
        this.boardSizeField =
                new JTextField(20);

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(30, 30, 30, 30));

        initializeUI();
    }

    private void initializeUI() {

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        new EmptyBorder(25, 30, 25, 30)
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel =
                new JLabel("TIC TAC TOE", SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        JLabel firstPlayerLabel =
                new JLabel("Player 1 Name:");

        JLabel secondPlayerLabel =
                new JLabel("Player 2 Name:");

        JLabel boardSizeLabel =
                new JLabel("Board Size:");

        JButton startButton =
                new JButton("START GAME");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(firstPlayerLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(firstPlayerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(secondPlayerLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(secondPlayerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(boardSizeLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(boardSizeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(startButton, gbc);

        startButton.addActionListener(
                e -> startGame()
        );

        add(formPanel);
    }

    private void startGame() {

        String firstPlayerName =
                firstPlayerNameField.getText().trim();

        String secondPlayerName =
                secondPlayerNameField.getText().trim();

        String boardSize =
                boardSizeField.getText().trim();

        if (firstPlayerName.isEmpty()
                || secondPlayerName.isEmpty()
                || boardSize.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both player names and board size",
                    "Missing Details",  
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int size;

        try {

            size = Integer.parseInt(boardSize);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Board size must be a number",
                    "Invalid Board Size",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (size < 3) {

            JOptionPane.showMessageDialog(
                    this,
                    "Board size must be at least 3",
                    "Invalid Board Size",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
        controller.startGame(
                size,
                firstPlayerName,
                secondPlayerName
        );

        listener.onPlayersSubmitted(
                firstPlayerName,
                secondPlayerName
        );
    }

    public interface PlayerSetupListener {

        void onPlayersSubmitted(
                String firstPlayerName,
                String secondPlayerName
        );
    }
}
