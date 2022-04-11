package chessGame;

import java.util.Objects;

public final class Position {
    private final int letter;
    private final int number;

    public Position(String position) throws WrongCellException {
        try {
            if (position.length() != 2) {
                throw new NumberFormatException();
            }

            switch (position.substring(0, 1)) {
                case "a" -> this.letter = 0;
                case "b" -> this.letter = 1;
                case "c" -> this.letter = 2;
                case "d" -> this.letter = 3;
                case "e" -> this.letter = 4;
                case "f" -> this.letter = 5;
                case "g" -> this.letter = 6;
                case "h" -> this.letter = 7;
                default -> throw new NumberFormatException();
            }

            int number = Integer.parseInt(position.substring(1, 2)) - 1;
            if (number > 7) {
                throw new NumberFormatException();
            } else {
                this.number = number;
            }
        } catch (NumberFormatException e) {
            throw new WrongCellException("Incorrect input");
        }
    }

    public Position (int number, int letter) {
        this.letter = letter;
        this.number = number;
    }

    public int getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Position{" +
                "letter=" + letter +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return letter == position.letter && number == position.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, number);
    }
}