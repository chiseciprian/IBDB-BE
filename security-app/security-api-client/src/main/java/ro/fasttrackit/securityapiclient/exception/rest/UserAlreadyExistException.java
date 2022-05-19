package ro.fasttrackit.securityapiclient.exception.rest;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
