package ticktactoe.src.model.Board;
import ticktactoe.src.model.PlayingPiece.PlayingPiece;

public class Board {
    private PlayingPiece[][] board;
    public int size;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece){
        if(board[row][column]== null){
            board[row][column] = playingPiece;
            return true;
        }
        return false;
    }


    public boolean isFull(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }



}
