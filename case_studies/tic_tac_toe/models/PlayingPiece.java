package case_studies.tic_tac_toe.models;

// Class representing a playing piece in the game
public class PlayingPiece {
    public PieceType pieceType; // The type of piece (X or O)
    public PlayingPiece(PieceType pieceType){
        this.pieceType = pieceType;
    }
}