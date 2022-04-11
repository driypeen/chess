package chessGame.figures;

import chessGame.Board;
import chessGame.Player;
import chessGame.Position;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Figure {
    public Knight(Player player) {
        super(player);
        super.code = '\u265e';
    }

    @Override
    public Set<Position> possibleMoves(Board board, Position from) {
        Set<Position> possibleMoves = new HashSet<>();

        int number = from.getNumber();
        int letter = from.getLetter();

        checkCell(board, new Position(number + 2, letter + 1), possibleMoves);
        checkCell(board, new Position(number + 2, letter - 1), possibleMoves);
        checkCell(board, new Position(number - 2, letter - 1), possibleMoves);
        checkCell(board, new Position(number - 2, letter + 1), possibleMoves);
        checkCell(board, new Position(number + 1, letter - 2), possibleMoves);
        checkCell(board, new Position(number - 1, letter - 2), possibleMoves);
        checkCell(board, new Position(number + 1, letter + 2), possibleMoves);
        checkCell(board, new Position(number - 1, letter + 2), possibleMoves);

        return possibleMoves;
    }
}
