package ticktactoe.src.model.PlayingPiece;
import ticktactoe.src.enums.Piece;

public class PlayingPiece {
    Piece piece;

    public PlayingPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
