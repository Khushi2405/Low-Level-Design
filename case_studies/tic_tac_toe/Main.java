package case_studies.tic_tac_toe;

import java.util.Objects;

public class Main {
    public static void main(String[] args){
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        String winner = game.startGame();
        System.out.println((Objects.equals(winner, "tie") ? "There is no winner game is tie" : winner + " wins the game"));
    }
}
