package ticktactoe.src.model.TickTacToeGame;

import java.util.ArrayDeque;
import java.util.Deque;

import ticktactoe.src.model.Board.Board;
import ticktactoe.src.model.Player.Player;
import ticktactoe.src.model.PlayingPiece.PlayingPiece;
import ticktactoe.src.model.PlayingPiece.PlayingPieceO;
import ticktactoe.src.model.PlayingPiece.PlayingPieceX;
import ticktactoe.src.enums.GameResultType;
import ticktactoe.src.model.GameResult.GameResult;

public class TickTacToeGame {
    private Board board;
    private Deque<Player> players;
    private Player currentPlayer;
    private GameResult gameResult;

    public void reset(){
        players = new ArrayDeque<Player>();
        gameResult = null;
        board = null;
        currentPlayer = null;
        gameResult = new GameResult(GameResultType.GAME_NOT_OVER,null);
    }
    public TickTacToeGame(){
        reset();
    }

    

    private Player addPlayer(String name){
        PlayingPiece x = null;
        switch (players.size()) {
            case 0:
                x = new PlayingPieceX();
                break;
            case 1:
                x = new PlayingPieceO();
            default:
                break;
        }
        Player player = new Player(name,x);
        players.add(player);
        return player;
    }

    private void createBoard(int size){
        this.board = new Board(size);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player startGame(
        int size,
        String player1,
        String player2
    ){
        reset();
        this.createBoard(size);
        Player player = this.addPlayer(player1);
        this.addPlayer(player2);
        currentPlayer = player;
        return player;
    }  

    private boolean isThereWinner(PlayingPiece playingPiece){
        PlayingPiece[][] board = this.board.getBoard();
        int size = this.board.size;
        for(int i=0;i<size;i++){
            boolean isRowWinner = true;
            boolean isColumnWinner = true;
            boolean isDiagonalWinner = true;
            for(int j=0;j<size;j++){
                if(board[i][j] != playingPiece){
                    isRowWinner = false;
                }
                if(board[j][i] != playingPiece){
                    isColumnWinner = false;
                }
                if(board[j][j] != playingPiece){
                    isDiagonalWinner = false;
                }
                if(board[j][size-j-1] != playingPiece){
                    isDiagonalWinner = false;
                }
            }
            if(isRowWinner || isColumnWinner || isDiagonalWinner){
                return true;
            }
        }
        return false;
    }
    
    public void turn(
        int row, 
        int column
    ){
        Player player = players.removeFirst();

        boolean isNewPeiceAdded = board.addPiece(row, column, player.getPlayingPiece());
        if(!isNewPeiceAdded){
            players.addFirst(player);
            gameResult.updateGameResult(GameResultType.INVALID_MOVE);
            return;
        }
        players.addLast(player);
        currentPlayer = players.peekFirst();
        boolean isGameOver = isThereWinner(player.getPlayingPiece());
        if(isGameOver){
            gameResult.updateGameResult(GameResultType.WIN);
            gameResult.updatePlayer(player);
            return;
        }
        boolean isBoardFull = board.isFull();
        if(isBoardFull){
            gameResult.updateGameResult(GameResultType.DRAW);
            return;
        }
        return;
    }

    public GameResult getGameResult(){
        return this.gameResult;
    }

}
