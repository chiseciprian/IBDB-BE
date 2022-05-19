package ro.fasttrackit.securityapiclient.exception.rest;

public class BadUserException extends RuntimeException {
    public BadUserException(String message) {
        super(message);
    }
}
