package case_studies.tic_tac_toe.models;

import com.sun.tools.javac.util.Pair;
import java.util.*;

// Class representing the Tic Tac Toe board
public class Board {
    public int size;
    public PlayingPiece[][] board;
    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    // Adds a piece to the board at the given row and column
    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(board[row][col] != null) return false; // Check if cell is already occupied
        board[row][col] = playingPiece; // Place piece
        return true;

    }

    // Returns a list of all empty cells on the board
    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size; j++){
                if(board[i][j] == null) list.add(new Pair<>(i,j));
            }
        }
        return list;
    }

    // Prints the current state of the board
    public void printBoard(){
        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size; j++){
                System.out.print("| " + (board[i][j] == null ? "  " : (board[i][j].pieceType + " ")));
            }
            System.out.println("|");
        }
    }

}
