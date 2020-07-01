package tictactoe.players;

import tictactoe.Cells;
import tictactoe.Field;

public class EasyAiPlayer implements Player {

    @Override
    public boolean move(Field field, Cells cell) {
        System.out.println("Making move level \"easy\"");
        return randomMove(field, cell);
    }
}
