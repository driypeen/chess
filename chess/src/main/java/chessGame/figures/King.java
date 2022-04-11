package chessGame.figures;

import chessGame.Board;
import chessGame.Player;
import chessGame.Position;

import java.util.HashSet;
import java.util.Set;

public class King extends Figure {
    public King(Player player) {
        super(player);
        code = '\u265a';
    }

    public Position shortCastling(Board board) {
        return null;
    }

    public Position longCastling(Board board) {
        return null;
    }

    @Override
    public Set<Position> possibleMoves(Board board, Position from) {
        Set<Position> possibleMoves = new HashSet<>();

        int number = from.getNumber();
        int letter = from.getLetter();

        checkCell(board, new Position(number + 1, letter + 1), possibleMoves);
        checkCell(board, new Position(number - 1, letter - 1), possibleMoves);
        checkCell(board, new Position(number - 1, letter + 1), possibleMoves);
        checkCell(board, new Position(number + 1, letter - 1), possibleMoves);
        checkCell(board, new Position(number - 1, letter), possibleMoves);
        checkCell(board, new Position(number + 1, letter), possibleMoves);
        checkCell(board, new Position(number, letter + 1), possibleMoves);
        checkCell(board, new Position(number, letter - 1), possibleMoves);


        return possibleMoves;
    }
}
