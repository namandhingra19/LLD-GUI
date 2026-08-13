package ticktactoe.src.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ticktactoe.src.controller.UIController;
import ticktactoe.src.enums.GameResultType;
import ticktactoe.src.model.GameResult.GameResult;
import ticktactoe.src.model.Player.Player;

import java.awt.*;

public class TicTacToeFrame extends JFrame {

    private static final String SETUP_SCREEN =
            "setup";

    private static final String GAME_SCREEN =
            "game";

    private final CardLayout cardLayout;
    private final JPanel rootPanel;
    private final UIController controller;
    private JLabel statusLabel;
    private boolean gameOver;

    public TicTacToeFrame(UIController uiController) {
        controller = uiController;
        cardLayout =
                new CardLayout();

        rootPanel =
                new JPanel(cardLayout);

        setTitle("Tic Tac Toe");
        setSize(700, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeUI();
    }

    private void initializeUI() {

        rootPanel.add(
                new PlayerSetupPanel(this::showGameScreen,this.controller),
                SETUP_SCREEN
        );

        setContentPane(rootPanel);

        cardLayout.show(rootPanel, SETUP_SCREEN);
    }

    private void showGameScreen(
            String firstPlayerName,
            String secondPlayerName
    ) {

        gameOver = false;

        rootPanel.add(
                createGamePanel(
                        firstPlayerName,
                        secondPlayerName
                ),
                GAME_SCREEN
        );

        cardLayout.show(rootPanel, GAME_SCREEN);
    }

    private JPanel createGamePanel(
            String firstPlayerName,
            String secondPlayerName
    ) {

        int boardSize =
                controller.getBoardSize();

        JPanel panel =
                new JPanel(new BorderLayout(20, 20));

        panel.setBorder(
                new EmptyBorder(25, 25, 25, 25)
        );

        panel.add(
                createHeader(
                        firstPlayerName,
                        secondPlayerName,
                        boardSize
                ),
                BorderLayout.NORTH
        );

        panel.add(
                createBoardPanel(boardSize),
                BorderLayout.CENTER
        );

        panel.add(
                createFooter(),
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JPanel createHeader(
            String firstPlayerName,
            String secondPlayerName,
            int boardSize
    ) {

        JPanel header =
                new JPanel(new GridLayout(2, 1, 5, 5));

        JLabel titleLabel =
                new JLabel("TIC TAC TOE", SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        JLabel playersLabel =
                new JLabel(
                        firstPlayerName + " (X)  vs  "
                                + secondPlayerName + " (O)  |  "
                                + boardSize + " x " + boardSize,
                        SwingConstants.CENTER
                );

        playersLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        header.add(titleLabel);
        header.add(playersLabel);

        return header;
    }

    private JPanel createBoardPanel(
            int boardSize
    ) {

        JPanel boardPanel =
                new JPanel(
                        new GridLayout(
                                boardSize,
                                boardSize,
                                8,
                                8
                        )
                );

        boardPanel.setBorder(
                new EmptyBorder(20, 80, 20, 80)
        );

        for (int index = 0;
                index < boardSize * boardSize;
                index++) {

            int row =
                    index / boardSize;

            int column =
                    index % boardSize;

            JButton cellButton =
                    new JButton("");

            cellButton.setFont(
                    new Font("Arial", Font.BOLD, 54)
            );

            cellButton.setFocusPainted(false);

            cellButton.addActionListener(
                    e -> handleCellClick(
                            cellButton,
                            row,
                            column
                    )
            );

            boardPanel.add(cellButton);
        }

        return boardPanel;
    }

    private JPanel createFooter() {

        JPanel footer =
                new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        statusLabel =
                new JLabel(getCurrentPlayerText());

        JButton resetButton =
                new JButton("NEW PLAYERS");

        resetButton.addActionListener(
                e -> cardLayout.show(rootPanel, SETUP_SCREEN)
        );

        footer.add(statusLabel);
        footer.add(resetButton);

        return footer;
    }

    private void handleCellClick(
            JButton cellButton,
            int row,
            int column
    ) {

        if (gameOver) {

            JOptionPane.showMessageDialog(
                    this,
                    "Game is already over",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (!cellButton.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "This cell is already occupied",
                    "Invalid Move",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Player currentPlayer =
                controller.getCurrentPlayer();

        if (currentPlayer == null
                || currentPlayer.getPlayingPiece() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please start the game first",
                    "Game Not Started",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        cellButton.setText(
                currentPlayer
                        .getPlayingPiece()
                        .getPiece()
                        .toString()
        );

        controller.turn(row, column);
        handleGameResult();
        updateStatus();
    }

    private void handleGameResult() {

        GameResult gameResult =
                controller.getGameResult();

        if (gameResult == null
                || gameResult.getGameResultType()
                == GameResultType.GAME_NOT_OVER) {

            return;
        }

        if (gameResult.getGameResultType()
                == GameResultType.INVALID_MOVE) {

            JOptionPane.showMessageDialog(
                    this,
                    gameResult.getMessage(),
                    "Invalid Move",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        gameOver = true;

        JOptionPane.showMessageDialog(
                this,
                gameResult.getMessage(),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void updateStatus() {

        if (statusLabel != null) {

            statusLabel.setText(
                    getCurrentPlayerText()
            );
        }
    }

    private String getCurrentPlayerText() {

        Player currentPlayer =
                controller.getCurrentPlayer();

        if (currentPlayer == null
                || currentPlayer.getPlayingPiece() == null) {

            return "Game ready";
        }

        return "Current turn: "
                + currentPlayer.getName()
                + " ("
                + currentPlayer
                        .getPlayingPiece()
                        .getPiece()
                + ")";
    }
}
