package _02_tictactoe.src;

import javax.swing.SwingUtilities;

import _02_tictactoe.src.controller.UIController;
import _02_tictactoe.src.model.TickTacToeGame.TickTacToeGame;
import _02_tictactoe.src.ui.TicTacToeFrame;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TickTacToeGame tickTacToeGame = new TickTacToeGame();
            UIController uiController = new UIController(tickTacToeGame);
            TicTacToeFrame frame =
                    new TicTacToeFrame(uiController);

            frame.setVisible(true);
        });
    }
}
