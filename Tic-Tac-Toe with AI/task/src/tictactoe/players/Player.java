package tictactoe.players;

import tictactoe.Cells;
import tictactoe.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tictactoe.Cells.EMPTY_CELL;

public interface Player {
    boolean move(Field field, Cells cell);

    static Player create(String type) {
        switch (type) {
            case "user":
                return new UserPlayer();
            case "easy":
                return new EasyAiPlayer();
            case "medium":
                return new MediumAiPlayer();
            case "hard":
                return new HardAIPlayer();
            default:
                return null;
        }
    }

    default boolean randomMove(Field field, Cells cell){
        Random random = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < field.FIELD_SIZE; i++) {
            for (int j = 0; j < field.FIELD_SIZE; j++) {
                if (field.getCell(new Point(i, j)) == EMPTY_CELL) {
                    points.add(new Point(i, j));
                }
            }
        }
        Point point = points.get(random.nextInt(points.size()));
        field.setCell(cell, point);
        return true;
    }
}
