package tictactoe;

import tictactoe.players.Player;

import java.util.*;

import static tictactoe.Cells.*;

public class TicTacToeGame {
    private final Scanner sc = new Scanner(System.in);
    private final Field field = new Field();
    private GameStatus gameStatus = GameStatus.GAME_NOT_FINISHED;
    private Player player1;
    private Player player2;

    public TicTacToeGame() {
        field.fill("_________".toCharArray());
    }

    private boolean play() {
        Player player = player1;
        Cells cell = field.getNumberOfO() < field.getNumberOfX() ? O_CELL : X_CELL;
        while (gameStatus == GameStatus.GAME_NOT_FINISHED) {
            System.out.println(field);
            if (!player.move(field, cell)) {
                return false;
            }
            gameStatus = field.getGameStatus();
            cell = cell == X_CELL ? O_CELL : X_CELL;
            player = player.equals(player1) ? player2 : player1;
        }
        System.out.println(field);
        System.out.println(gameStatus.getText());
        return true;
    }


    public void run() {
        String errorText = "Bad parameters!";
        while (true) {
            System.out.print("Input command: ");
            String parameters = sc.nextLine();
            System.out.println(parameters);
            String[] args = parameters.split(" ");
            switch (args[0]) {
                case "start":
                    if (args.length != 3) {
                        System.out.println(errorText);
                        continue;
                    }
                    player1 = Player.create(args[1]);
                    player2 = Player.create(args[2]);
                    if (player2 == null || player1 == null){
                        System.out.println(errorText);
                        continue;
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println(errorText);
                    continue;
            }
            if (!play()) {
                return;
            }
            field.clear();
            gameStatus = GameStatus.GAME_NOT_FINISHED;
        }
    }
}
