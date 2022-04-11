package chessGame;

import chessGame.figures.*;

import java.util.*;

public class Board {
    private final Figure[][] field = new Figure[8][8];
    private final List<Figure> lostFigures = new ArrayList<>();
    private final static String POSSIBLE_EMPTY_CELL = Colors.GREEN + "\u25cc" + Colors.RESET;
    private Position blackKingPosition;
    private Position whiteKingPosition;

    public void setFigures () {
        for (int i = 0; i < 8; i++) {
            field[1][i] = new Pawn(Player.BLACK_PLAYER);
            field[6][i] = new Pawn(Player.WHITE_PLAYER);
        }

        field[0][3] = new Queen(Player.BLACK_PLAYER);
        field[7][4] = new Queen(Player.WHITE_PLAYER);

        field[0][4] = new King(Player.BLACK_PLAYER);
        blackKingPosition = new Position(0, 4);
        field[7][3] = new King(Player.WHITE_PLAYER);
        whiteKingPosition = new Position(7, 3);

        field[0][0] = new Castle(Player.BLACK_PLAYER);
        field[0][7] = new Castle(Player.BLACK_PLAYER);
        field[7][0] = new Castle(Player.WHITE_PLAYER);
        field[7][7] = new Castle(Player.WHITE_PLAYER);

        field[0][1] = new Knight(Player.BLACK_PLAYER);
        field[0][6] = new Knight(Player.BLACK_PLAYER);
        field[7][1] = new Knight(Player.WHITE_PLAYER);
        field[7][6] = new Knight(Player.WHITE_PLAYER);

        field[0][2] = new Bishop(Player.BLACK_PLAYER);
        field[0][5] = new Bishop(Player.BLACK_PLAYER);
        field[7][2] = new Bishop(Player.WHITE_PLAYER);
        field[7][5] = new Bishop(Player.WHITE_PLAYER);
    }

    private void printTopLine () {
        System.out.println("   A     B     C     D     E     F     G     H");
        System.out.print("\u2554\u2550\u2550\u2550\u2550\u2550");
        for (int i = 0; i < 7; i++) {
            System.out.print("\u2566\u2550\u2550\u2550\u2550\u2550");
        }
        System.out.println('\u2557');
    }

    private void printMiddleLine () {
        System.out.print("\u2560\u2550\u2550\u2550\u2550\u2550");
        for (int j = 0; j < 7; j++) {
            System.out.print("\u256c\u2550\u2550\u2550\u2550\u2550");
        }
        System.out.println('\u2563');
    }

    private void printBottomLine () {
        System.out.print("\u255a\u2550\u2550\u2550\u2550\u2550");
        for (int i = 0; i < 7; i++) {
            System.out.print("\u2569\u2550\u2550\u2550\u2550\u2550");
        }
        System.out.println('\u255d');
    }

    private void printDefaultCell (Figure cell) {
        if (cell == null) {
            System.out.print(' ');
        } else {
            cell.defaultPrint();
        }
    }

    private void printPossibleMove (Figure cell) {
        if (cell == null) {
            System.out.print(POSSIBLE_EMPTY_CELL);
        } else {
            cell.possibleMovesPrint();
        }
    }

    private void printFiguresLine (int lineNumber, Position selectedCell, Set<Position> possibleMoves) {
        System.out.print('\u2551');
        for (int i = 0; i < 8; i++) {
            Figure cell = field[lineNumber][i];
            System.out.print("  ");

            if (selectedCell != null
                && selectedCell.getLetter() == i
                    && selectedCell.getNumber() == lineNumber) {
                cell.selectedFigurePrint();
                System.out.print("  \u2551");
                continue;
            }

            if (possibleMoves == null) {
                printDefaultCell(cell);
            } else {
                Position current = new Position(lineNumber, i);
                if (possibleMoves.contains(current)) {
                    printPossibleMove(cell);
                } else {
                    printDefaultCell(cell);
                }
            }
            System.out.print("  \u2551");
        }
        System.out.println(" " + (lineNumber + 1));
    }

    public void printBoard (Position selectedCell, Set<Position> possibleMoves) {
        printTopLine();
        for (int i = 0; i < 7; i++) {
            printFiguresLine(i, selectedCell, possibleMoves);
            printMiddleLine();
        }
        printFiguresLine(7, selectedCell, possibleMoves);
        printBottomLine();
    }

    public void setCell (Figure figure, Position position) throws WrongCellException {
        field[position.getNumber()][position.getLetter()] = figure;
    }

    public Figure getCell (String cell) throws WrongCellException {
        Position position = new Position(cell);
        return field[position.getNumber()][position.getLetter()];
    }

    public Figure getCell (Position cell) throws WrongCellException {
        return field[cell.getNumber()][cell.getLetter()];
    }

    public void move (Position from, Position to) throws WrongCellException {
        getCell(from).move();

        Figure target = getCell(to);
        if (target != null) {
            lostFigures.add(target);
        }

        setCell(getCell(from), to);
        setCell(null, from);

        if (from.equals(whiteKingPosition)) {
            whiteKingPosition = to;
        }
        if (from.equals(blackKingPosition)) {
            blackKingPosition = to;
        }
    }
}
