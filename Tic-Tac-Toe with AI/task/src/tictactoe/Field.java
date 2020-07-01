package tictactoe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static tictactoe.Cells.*;

public class Field {
    public final int FIELD_SIZE = 3;
    private final Cells[][] field = new Cells[FIELD_SIZE][FIELD_SIZE];
    private int numberOfX = 0;
    private int numberOfO = 0;


    public void countEntities() {
        numberOfX = 0;
        numberOfO = 0;
        for (Cells[] cells : field) {
            for (Cells cell : cells) {
                if (cell == X_CELL) {
                    numberOfX++;
                }
                if (cell == O_CELL) {
                    numberOfO++;
                }
            }
        }
    }

    public GameStatus getGameStatus() {
        int xSum = X_CELL.getCh() * FIELD_SIZE;
        int oSum = O_CELL.getCh() * FIELD_SIZE;
        numberOfX = 0;
        numberOfO = 0;
        boolean xWin = false;
        boolean oWin = false;
        boolean isEmpty = false;
        int diag1Sum = 0;
        int diag2Sum = 0;
        for (int row = 0; row < FIELD_SIZE; row++) {
            int rowSum = 0;
            int colSum = 0;
            diag1Sum += field[row][row].getCh();
            diag2Sum += field[row][FIELD_SIZE - row - 1].getCh();
            for (int col = 0; col < FIELD_SIZE; col++) {
                Cells cell = field[row][col];
                numberOfX += cell == X_CELL ? 1 : 0;
                numberOfO += cell == O_CELL ? 1 : 0;
                isEmpty = cell == EMPTY_CELL || isEmpty;
                rowSum += field[row][col].getCh();
                colSum += field[col][row].getCh();
            }
            xWin = rowSum == xSum || xWin;
            oWin = rowSum == oSum || oWin;
            xWin = colSum == xSum || xWin;
            oWin = colSum == oSum || oWin;
            xWin = diag1Sum == xSum || xWin;
            oWin = diag1Sum == oSum || oWin;
            xWin = diag2Sum == xSum || xWin;
            oWin = diag2Sum == oSum || oWin;
        }
        if (xWin && oWin || Math.abs(numberOfO - numberOfX) > 1) {
            return GameStatus.IMPOSSIBLE;
        }
        if (xWin) {
            return GameStatus.X_WINS;
        }
        if (oWin) {
            return GameStatus.O_WINS;
        }
        if (isEmpty) {
            return GameStatus.GAME_NOT_FINISHED;
        }
        return GameStatus.DRAW;
    }

    public void fill(char[] chars) {
        Cells[] cells = new Cells[chars.length];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = Cells.getInstance(chars[i]);
        }
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                int index = (row * FIELD_SIZE + col);
                field[row][col] = cells[index];
            }
        }
        countEntities();
    }

    public void clear() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                field[row][col] = EMPTY_CELL;
            }
        }
    }

    public Cells getCell(Point point) {
        return field[point.x][point.y];
    }

    public void setCell(Cells cell, Point point) {
        field[point.x][point.y] = cell;
    }

    public boolean isEmptyLeft() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (field[row][col] == EMPTY_CELL) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWinning(Cells cell) {
        int sum = cell.getCh() * FIELD_SIZE;
        boolean win = false;
        int diag1Sum = 0;
        int diag2Sum = 0;
        for (int row = 0; row < FIELD_SIZE; row++) {
            int rowSum = 0;
            int colSum = 0;
            diag1Sum += field[row][row].getCh();
            diag2Sum += field[row][FIELD_SIZE - row - 1].getCh();
            for (int col = 0; col < FIELD_SIZE; col++) {
                rowSum += field[row][col].getCh();
                colSum += field[col][row].getCh();
            }
            win = rowSum == sum;
            win = colSum == sum || win;
            win = diag1Sum == sum || win;
            win = diag2Sum == sum || win;
            if (win) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfX() {
        return numberOfX;
    }

    public int getNumberOfO() {
        return numberOfO;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < FIELD_SIZE + 2; row++) {
            for (int col = 0; col < FIELD_SIZE * FIELD_SIZE; col++) {
                if (row == 0 || row == FIELD_SIZE + 1) {
                    stringBuilder.append('-');
                } else if (col == 0 || col == FIELD_SIZE * FIELD_SIZE - 1) {
                    stringBuilder.append('|');
                } else if (col % 2 == 0) {
                    int r = row - 1;
                    int c = col / 2 - 1;
                    stringBuilder.append(field[r][c].getCh());
                } else {
                    stringBuilder.append(' ');
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
