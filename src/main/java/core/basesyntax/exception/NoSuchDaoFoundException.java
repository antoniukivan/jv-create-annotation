package core.basesyntax.exception;

public class NoSuchDaoFoundException extends RuntimeException {
    public NoSuchDaoFoundException(String message) {
        super(message);
    }
}
