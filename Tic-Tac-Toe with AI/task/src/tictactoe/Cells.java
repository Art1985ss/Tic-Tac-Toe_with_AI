package tictactoe;

import java.util.Arrays;

public enum Cells {
    X_CELL('X'),
    O_CELL('O'),
    EMPTY_CELL('_');

    private final char ch;

    Cells(char ch) {
        this.ch = ch;
    }

    public char getCh() {
        return ch;
    }

    public static Cells getInstance(char ch) {
        return Arrays.stream(values())
                .filter(cells -> cells.ch == ch)
                .findFirst().orElse(EMPTY_CELL);
    }
}
