package chessGame.figures;

import chessGame.Board;
import chessGame.Player;
import chessGame.Position;

import java.util.HashSet;
import java.util.Set;

public class Castle extends Figure {
    public Castle(Player player) {
        super(player);
        code = '\u265c';
    }

    @Override
    public Set<Position> possibleMoves(Board board, Position from) {
        Set<Position> possibleMoves = new HashSet<>();
        horizontalLine(board, from, possibleMoves);
        verticalLine(board, from, possibleMoves);

        return possibleMoves;
    }
}
