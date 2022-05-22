package ro.fasttrackit.securityservice.controller.exception;

import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.fasttrackit.exceptions.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(AuthenticationFailException.class)
    @ResponseStatus(NOT_FOUND)
    ApiError handleAuthenticationFailException(AuthenticationFailException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(BadUserException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleBadUserException(BadUserException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(MissingAuthorizationException.class)
    @ResponseStatus(UNAUTHORIZED)
    ApiError handleMissingAuthorizationException(MissingAuthorizationException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleValidationException(ValidationException ex) {
        return new ApiError(ex.getMessage());
    }
}

@Value
class ApiError {
    String message;
}
