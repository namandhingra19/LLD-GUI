package ticktactoe.src;

import javax.swing.SwingUtilities;

import ticktactoe.src.controller.UIController;
import ticktactoe.src.model.TickTacToeGame.TickTacToeGame;
import ticktactoe.src.ui.TicTacToeFrame;

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
