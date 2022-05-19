package ro.fasttrackit.securityapiclient.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User name and/or password is wrong")
public class AuthenticationFailException extends RuntimeException {

    public AuthenticationFailException(String message) {
        super(message);
    }
}
