package ticktactoe.src.model.GameResult;
import ticktactoe.src.enums.GameResultType;
import ticktactoe.src.model.Player.Player;

public class GameResult {
    private GameResultType gameResultType;
    private Player player;
    private boolean endtheGame;

    public GameResult(GameResultType gameResultType, Player player) {
        this.gameResultType = gameResultType;
        this.player = player;
        this.endtheGame = false;
    }

    public GameResultType getGameResultType() {
        return gameResultType;
    }

    public Player getPlayer() {
        return player;
    }

    public void updateGameResult(GameResultType gameResultType){
        this.gameResultType = gameResultType;
    }

    public void updatePlayer(Player player){
        this.player = player;
    }

    public boolean endTheGame(){
        return this.endtheGame;
    }

    public String getMessage() {
        switch (gameResultType) {
            case WIN:
                endtheGame = true;
                return "Player " + player.getName() + " wins!";
            case LOSE:
                endtheGame = true;
                return "Player " + player.getName() + " loses!";
            case DRAW:
                endtheGame = true;
                return "It's a draw!";
            case INVALID_MOVE:
                return "Invalid move!";
            case GAME_NOT_OVER:
                return "Game is not over yet!";
            default:
                return "";
        }
    }
}
