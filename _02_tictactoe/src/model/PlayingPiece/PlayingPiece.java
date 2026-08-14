package _02_tictactoe.src.model.PlayingPiece;
import _02_tictactoe.src.enums.Piece;

public class PlayingPiece {
    Piece piece;

    public PlayingPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
