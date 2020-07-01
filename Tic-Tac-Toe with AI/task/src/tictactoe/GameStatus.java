package tictactoe;

public enum GameStatus {
    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");

    private final String text;

    GameStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
