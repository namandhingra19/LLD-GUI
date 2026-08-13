package ticktactoe.src.model.Player;

import ticktactoe.src.model.PlayingPiece.PlayingPiece;

public class Player {
    PlayingPiece playingPiece;
    String name;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.playingPiece = playingPiece;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }

    public String getName() {   
        return name;
    }
}
