package tictactoe.players;

import tictactoe.Cells;
import tictactoe.Field;

import java.awt.*;

public class MediumAiPlayer implements Player {
    boolean moved = false;

    @Override
    public boolean move(Field field, Cells cell) {
        System.out.println("Making move level \"medium\"");
        int myNumber = cell.getCh() * 2 + Cells.EMPTY_CELL.getCh();
        int enemyNumber = (cell == Cells.X_CELL ? Cells.O_CELL : Cells.X_CELL).getCh() * 2 + Cells.EMPTY_CELL.getCh();
        int diag1Sum = 0;
        int diag2Sum = 0;
        int[] rowSums = new int[field.FIELD_SIZE];
        int[] colSums = new int[field.FIELD_SIZE];
        for (int row = 0; row < field.FIELD_SIZE; row++) {
            diag1Sum += field.getCell(new Point(row, row)).getCh();
            diag2Sum += field.getCell(new Point(row, field.FIELD_SIZE - row - 1)).getCh();
            for (int col = 0; col < field.FIELD_SIZE; col++) {
                rowSums[row] += field.getCell(new Point(row, col)).getCh();
                colSums[row] += field.getCell(new Point(col, row)).getCh();
            }
        }
        moved = false;
        Point point = null;
        for (int i = 0; i < field.FIELD_SIZE; i++) {
            if (rowSums[i] == myNumber && !moved) {
                point = makeMove(field, i, true);
            }
            if (colSums[i] == myNumber && !moved) {
                point = makeMove(field, i, false);
            }
            if (rowSums[i] == enemyNumber && !moved) {
                point = makeMove(field, i, true);
            }
            if (colSums[i] == enemyNumber && !moved) {
                point = makeMove(field, i, false);
            }
        }
        if (diag1Sum == myNumber && !moved) {
            point = makeDiagMove(field, true);
        }
        if (diag2Sum == myNumber && !moved) {
            point = makeDiagMove(field, false);
        }
        if (diag1Sum == enemyNumber && !moved) {
            point = makeDiagMove(field, true);
        }
        if (diag2Sum == enemyNumber && !moved) {
            point = makeDiagMove(field, false);
        }
        if (point == null) {
            return randomMove(field, cell);
        }
        field.setCell(cell, point);
        return true;
    }

    private Point makeMove(Field field, int line, boolean isRow) {
        Point resultPoint = null;
        for (int i = 0; i < field.FIELD_SIZE; i++) {
            Point point;
            if (isRow) {
                point = new Point(line, i);
            } else {
                point = new Point(i, line);
            }
            if (field.getCell(point) == Cells.EMPTY_CELL) {
                resultPoint = point;
                break;
            }
        }
        moved = true;
        return resultPoint;
    }

    private Point makeDiagMove(Field field, boolean isMain) {
        Point resultPoint = null;
        for (int i = 0; i < field.FIELD_SIZE; i++) {
            Point point;
            if (isMain) {
                point = new Point(i, i);
            } else {
                point = new Point(i, field.FIELD_SIZE - i - 1);
            }
            if (field.getCell(point) == Cells.EMPTY_CELL) {
                resultPoint = point;
                break;
            }
        }
        moved = true;
        return resultPoint;
    }
}
