package tictactoe.players;

import tictactoe.Cells;
import tictactoe.Field;

import java.awt.*;

public class HardAIPlayer implements Player {
    @Override
    public boolean move(Field field, Cells cell) {
        System.out.println("Making move level \"hard\"");
        Point point = new Point(-1, -1);
        int bestVal = Integer.MIN_VALUE;
        for (int row = 0; row < field.FIELD_SIZE; row++) {
            for (int col = 0; col < field.FIELD_SIZE; col++) {
                Point p = new Point(row, col);
                Cells currCell = field.getCell(p);
                if (currCell == Cells.EMPTY_CELL) {
                    field.setCell(cell, p);
                    int moveVal = minimax(field, cell, 0, false);
                    field.setCell(Cells.EMPTY_CELL, p);
                    if (moveVal > bestVal) {
                        point.x = row;
                        point.y = col;
                        bestVal = moveVal;
                    }
                }
            }
        }
        field.setCell(cell, point);
        return true;
    }

    private int minimax(Field field, Cells cell, int depth, boolean isMax) {
        if (field.isWinning(cell)) {
            return 10;
        }
        if (field.isWinning(cell == Cells.X_CELL ? Cells.O_CELL : Cells.X_CELL)) {
            return -10;
        }
        if (!field.isEmptyLeft()) {
            return 0;
        }
        int best;
        if (isMax) {
            best = Integer.MIN_VALUE;
            for (int row = 0; row < field.FIELD_SIZE; row++) {
                for (int col = 0; col < field.FIELD_SIZE; col++) {
                    Point point = new Point(row, col);
                    Cells currCell = field.getCell(point);
                    if (currCell == Cells.EMPTY_CELL) {
                        field.setCell(cell, point);
                        int minimax = minimax(field, cell, depth + 1, false);
                        best = Math.max(best, minimax);
                        field.setCell(Cells.EMPTY_CELL, point);
                    }
                }
            }
        } else {
            best = Integer.MAX_VALUE;
            for (int row = 0; row < field.FIELD_SIZE; row++) {
                for (int col = 0; col < field.FIELD_SIZE; col++) {
                    Point point = new Point(row, col);
                    Cells currCell = field.getCell(point);
                    if (currCell == Cells.EMPTY_CELL) {
                        field.setCell(cell == Cells.X_CELL ? Cells.O_CELL : Cells.X_CELL, point);
                        int minimax = minimax(field, cell, depth + 1, true);
                        best = Math.min(best, minimax);
                        field.setCell(Cells.EMPTY_CELL, point);
                    }
                }
            }
        }
        return best;
    }
}
