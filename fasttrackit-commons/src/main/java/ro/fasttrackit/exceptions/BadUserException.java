package ro.fasttrackit.exceptions;

public class BadUserException extends RuntimeException {
    public BadUserException(String message) {
        super(message);
    }
}
