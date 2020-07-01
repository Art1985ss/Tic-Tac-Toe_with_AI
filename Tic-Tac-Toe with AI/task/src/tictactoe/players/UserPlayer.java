package tictactoe.players;

import tictactoe.Cells;
import tictactoe.Field;

import java.awt.*;
import java.util.Scanner;

public class UserPlayer implements Player {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public boolean move(Field field, Cells cell) {
        int row;
        int col;
        while (true) {
            System.out.print("Enter the coordinates:");
            String coordinates = sc.nextLine();
            if (coordinates.contains("exit")) {
                return false;
            }
            if (coordinates.matches("(.*)[A-Za-z](.*)")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (coordinates.split(" ").length != 2) {
                System.out.println("Should not be more than 2 coordinates!");
                continue;
            }
            if (coordinates.matches("(.*)[4-90](.*)")) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            String[] str = coordinates.split(" ");
            col = Integer.parseInt(str[0]);
            row = Integer.parseInt(str[1]);
            col -= 1;
            row = field.FIELD_SIZE - row;
            if (Cells.EMPTY_CELL != field.getCell(new Point(row, col))) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;
        }
        field.setCell(cell, new Point(row, col));
        return true;
    }
}
