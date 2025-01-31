package case_studies.tic_tac_toe;

import case_studies.tic_tac_toe.models.*;
import com.sun.tools.javac.util.Pair;

import java.util.*;

// Class representing the Tic Tac Toe game logic
public class TicTacToeGame {
    Deque<Player> players; // Dequeue to manage player turns, pick from first and add to last after turn
    Board gameBoard;

    // Initializes the game with two players and a 3x3 board, can be scalable
    public void initializeGame(){
        players = new LinkedList<>();

        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", playingPieceX);

        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player player2 = new Player("Player2", playingPieceO);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame(){
        boolean noWinner = true;
        while (noWinner){
            Player currPlayer = players.removeFirst(); // Get the current player

            gameBoard.printBoard();
            List<Pair<Integer, Integer>> list = gameBoard.getFreeCells();
            if(list.isEmpty()){
                noWinner = false; // If no empty cells, game is a tie
                continue;
            }

            // Take user input for the next move
            System.out.println("Player " + currPlayer.getName() + ". Enter row and column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputCol = Integer.parseInt(values[1]);

            boolean isPieceAdded = gameBoard.addPiece(inputRow, inputCol, currPlayer.getPlayingPiece());
            if(!isPieceAdded){
                System.out.println("Incorrect position, please try again");
                players.addFirst(currPlayer); // Re-add player to the front if move is invalid
                continue;
            }
            players.addLast(currPlayer); // Add player back to queue for their next turn

            boolean isWinner = isThereWinner(inputRow, inputCol, currPlayer.getPlayingPiece().pieceType);
            if(isWinner){
                return currPlayer.getName(); // Return winner name
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row, int col, PieceType pieceType){

        boolean isRow = true;
        for(int i = 0 ; i < gameBoard.size; i++){
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                isRow = false;
                break;
            }
        }
        if(isRow) return true;
        boolean isCol = true;
        for(int i = 0 ; i < gameBoard.size; i++){
            if (gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) {
                isCol = false;
                break;
            }
        }
        if(isCol) return true;
        boolean isLeftDiagonal = true;
        for(int i = 0 ; i < gameBoard.size; i++){
            if (gameBoard.board[i][i] == null || gameBoard.board[i][i].pieceType != pieceType) {
                isLeftDiagonal = false;
                break;
            }
        }
        if(isLeftDiagonal) return true;
        boolean isRightDiagonal = true;
        for(int i = 0 ; i < gameBoard.size; i++){
            if(gameBoard.board[i][gameBoard.size - i - 1] == null || gameBoard.board[i][gameBoard.size - 1 -i].pieceType != pieceType){
                isRightDiagonal = false;
                break;
            }
        }
        return isRightDiagonal;

    }
}
