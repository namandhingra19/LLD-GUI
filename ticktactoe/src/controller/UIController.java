package ticktactoe.src.controller;

import ticktactoe.src.model.GameResult.GameResult;
import ticktactoe.src.model.Player.Player;
import ticktactoe.src.model.TickTacToeGame.TickTacToeGame;

public class UIController {
    private TickTacToeGame tickTacToeGame;
    public UIController(TickTacToeGame tickTacToeGame){
        this.tickTacToeGame = tickTacToeGame;
    }

    public Player startGame(
        int size,
        String player1,
        String player2
    ){
        return tickTacToeGame.startGame(size, player1, player2);
    }

    public boolean addPiece(
        int row,
        int column,
        Player player
    ){
        return tickTacToeGame.getBoard().addPiece(row, column, player.getPlayingPiece());
    }   

    public Player getCurrentPlayer(){
        return tickTacToeGame.getCurrentPlayer();
    }

    public int getBoardSize(){
        return tickTacToeGame.getBoard().size;
    }

    public void turn(int row, int column){  
        tickTacToeGame.turn(row, column);
    }

    public GameResult getGameResult(){
        return tickTacToeGame.getGameResult();
    }

}
