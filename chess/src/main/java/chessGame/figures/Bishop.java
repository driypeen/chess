package chessGame.figures;

import chessGame.Board;
import chessGame.Player;
import chessGame.Position;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Figure {
    public Bishop(Player player) {
        super(player);
        code = '\u265d';
    }

    @Override
    public Set<Position> possibleMoves(Board board, Position from) {
        Set<Position> possibleMoves = new HashSet<>();
        diagonalLine(board, from, possibleMoves);

        return possibleMoves;
    }
}
