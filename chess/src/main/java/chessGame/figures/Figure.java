package chessGame.figures;

import chessGame.Board;
import chessGame.Colors;
import chessGame.Player;
import chessGame.Position;

import java.util.Set;

public abstract class Figure {
    protected final Player player;
    private boolean isNotMoved;
    protected char code;

    protected Figure(Player player) {
        this.player = player;
        this.isNotMoved = true;
    }

    protected boolean isNotMoved() {
        return isNotMoved;
    }
    public void move() {
        isNotMoved = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void defaultPrint() {
        switch (player) {
            case BLACK_PLAYER -> System.out.print(Colors.BLACK.toString() + code + Colors.RESET);
            case WHITE_PLAYER -> System.out.print(code);
        }
    }

    public void possibleMovesPrint() {
        System.out.print(Colors.GREEN.toString() + code + Colors.RESET);
    }

    public void selectedFigurePrint() {
        System.out.print(Colors.YELLOW.toString() + code + Colors.RESET);
    }

    protected boolean isLastCellOnLine(Board board, Position current, Set<Position> possibleMoves) {
        try {
            Figure figure = board.getCell(current);

            if (figure == null) {
                possibleMoves.add(current);
                return false;
            } else if (figure.getPlayer() != player) {
                possibleMoves.add(current);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    protected void horizontalLine(Board board, Position from, Set<Position> possibleMoves) {
        for (int i = from.getLetter() + 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(from.getNumber(), i), possibleMoves)) {
                break;
            }
        }

        for (int i = from.getLetter() - 1; i >= 0; i--) {
            if (isLastCellOnLine(board, new Position(from.getNumber(), i), possibleMoves)) {
                break;
            }
        }
    }

    protected void verticalLine(Board board, Position from, Set<Position> possibleMoves) {
        for (int i = from.getNumber() + 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(i, from.getLetter()), possibleMoves)) {
                break;
            }
        }

        for (int i = from.getNumber() - 1; i > 0; i--) {
            if (isLastCellOnLine(board, new Position(i, from.getLetter()), possibleMoves)) {
                break;
            }
        }
    }

    protected void diagonalLine(Board board, Position from, Set<Position> possibleMoves) {
        int number = from.getNumber();
        int letter = from.getLetter();

        for (int i = 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(number + i, letter + i), possibleMoves)) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(number - i, letter - i), possibleMoves)) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(number + i, letter - i), possibleMoves)) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (isLastCellOnLine(board, new Position(number - i, letter + i), possibleMoves)) {
                break;
            }
        }
    }

    protected void checkCell(Board board, Position toCheck, Set<Position> possibleMoves) {
        if (toCheck.getLetter() > 7 || toCheck.getNumber() > 7
                || toCheck.getNumber() < 0 || toCheck.getLetter() < 0) {
            return;
        }

        if (board.getCell(toCheck) == null || board.getCell(toCheck).getPlayer() != player) {
            possibleMoves.add(toCheck);
        }
    }

    public abstract Set<Position> possibleMoves (Board board, Position from);
}
