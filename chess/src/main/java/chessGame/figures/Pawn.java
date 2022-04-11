package chessGame.figures;

import chessGame.Board;
import chessGame.Player;
import chessGame.Position;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pawn extends Figure {
    public Pawn(Player player) {
        super(player);
        code = '\u265f';
    }

    private void defaultMove(Board board, Position from, Set<Position> possibleMoves) {
        int shortMove = 0, longMove = 0;

        switch (player) {
            case WHITE_PLAYER -> {
                shortMove = -1;
                longMove = -2;
            }
            case BLACK_PLAYER ->  {
                shortMove = 1;
                longMove = 2;
            }
        }

        Position shortMoveTo = new Position(from.getNumber() + shortMove,
                from.getLetter());

        if (board.getCell(shortMoveTo) == null) {
            possibleMoves.add(shortMoveTo);

            Position longMoveTo = new Position(from.getNumber() + longMove,
                    from.getLetter());

            if (isNotMoved() && board.getCell(longMoveTo) == null) {
                possibleMoves.add(longMoveTo);
            }
        }
    }

    private boolean checkEnemy(Board board, Position position) {
        try {
            return board.getCell(position).getPlayer() != player;
        } catch (Exception e) {
            return false;
        }
    }

    private void toTake(Board board, Position from, Set<Position> possibleMoves) {
        byte move = 1;

        if (player == Player.WHITE_PLAYER) {
            move = -1;
        }

        Position left = new Position(from.getNumber() + move, from.getLetter() + 1);
        Position right = new Position(from.getNumber() + move, from.getLetter() - 1);

        if (checkEnemy(board, left)) {
            possibleMoves.add(left);
        }

        if (checkEnemy(board, right)) {
            possibleMoves.add(right);
        }
    }

    public void changeToAnotherFigure (Scanner in, Board board, Position from) {
        System.out.println("Change new figure:");
        System.out.println("1-queen, 2-castle, 3-bishop, 4-knight.");

        while (true) {
            try {
                switch (in.nextInt()) {
                    case 1 -> board.setCell(new Queen(player), from);
                    case 2 -> board.setCell(new Castle(player), from);
                    case 3 -> board.setCell(new Bishop(player), from);
                    case 4 -> board.setCell(new Knight(player), from);
                }
                break;
            } catch (Exception e) {
                System.out.println("Incorrect input.");
            }
        }
    }

    @Override
    public Set<Position> possibleMoves (Board board, Position from) {
        Set<Position> possibleMoves = new HashSet<>();
        defaultMove(board, from, possibleMoves);
        toTake(board, from, possibleMoves);

        return possibleMoves;
    }
}
