package chessGame;

import chessGame.figures.King;
import chessGame.figures.Pawn;

import java.util.Scanner;
import java.util.Set;

public final class Chess {
    private static final String INCORRECT_INPUT = "Incorrect input.";

    final Board board = new Board();

    private boolean isCheckmate () {
        return false;
    }

    public void startGame () {
        board.setFigures();
        Scanner in = new Scanner(System.in);
        Player currentPlayer = Player.WHITE_PLAYER;

        while (!isCheckmate()) {
            board.printBoard(null,null);
            makeMove(currentPlayer, in);
            if (currentPlayer == Player.BLACK_PLAYER) {
                currentPlayer = Player.WHITE_PLAYER;
            } else {
                currentPlayer = Player.BLACK_PLAYER;
            }
        }
    }

    public Set<Position> getPossibleMoves(Position position) {
        return board.getCell(position).possibleMoves(board, position);
    }

    private Position inputCell (String moveFrom, Player player, Scanner in) {
        Position result;
        while (true) {
            try {
                result = new Position(moveFrom);

                if (board.getCell(moveFrom) == null || board.getCell(moveFrom).getPlayer() != player) {
                    System.out.println("I haven't figure on this space.");
                    throw new WrongCellException("There are no figure.");
                } else {
                    break;
                }
            } catch (WrongCellException e) {
                System.out.println(INCORRECT_INPUT);
                moveFrom = in.next();
            }
        }

        return result;
    }

    public void makeMove (Player player, Scanner in) {
        switch (player) {
            case BLACK_PLAYER -> System.out.println("Black turn:");
            case WHITE_PLAYER -> System.out.println("White turn:");
        }

        String moveFrom = in.next();
        Position selected;
        Set<Position> possibleMoves;

        while (true) {
            try {
                selected = inputCell(moveFrom,player,in);
                possibleMoves = getPossibleMoves(selected);

                if (possibleMoves.isEmpty()) {
                    throw new WrongCellException("No way to move.");
                } else {
                    break;
                }
            } catch (WrongCellException e) {
                System.out.println(e.getMessage());
                moveFrom = in.next();
            }
        }

        board.printBoard(selected,possibleMoves);
        String moveTo;

        while (true) {
            moveTo = in.next();
            Position posTo;
            try {
                posTo = new Position(moveTo);

                if (possibleMoves.contains(posTo)) {
                    break;
                } else {
                    System.out.println("You cannot move here.");
                }
            } catch (WrongCellException e) {
                System.out.println(INCORRECT_INPUT);
            }
        }

        Position posTo =  new Position(moveTo);
        board.move(selected, posTo);

        if (board.getCell(posTo) instanceof Pawn && (
                posTo.getNumber() == 0 && player == Player.WHITE_PLAYER ||
                        posTo.getNumber() == 7 && player == Player.BLACK_PLAYER)) {
            ((Pawn) board.getCell(moveTo)).changeToAnotherFigure(in, board, posTo);
        }
    }

}
