package chessGame;

public class
WrongCellException extends RuntimeException {
    public WrongCellException (String errorMessage) {
        super(errorMessage);
    }
}
