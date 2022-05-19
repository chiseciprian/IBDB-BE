package ro.fasttrackit.securityapiclient.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.fasttrackit.securityapiclient.exception.error.RestErrorDto;
import ro.fasttrackit.securityapiclient.exception.rest.UserAlreadyExistException;
import ro.fasttrackit.securityapiclient.rest.AccountControllerApi;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = AccountControllerApi.class)
public class AccountControllerExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    protected ResponseEntity<Object> setUserAlreadyExistException(UserAlreadyExistException e) {
        return setResponse(e, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> setResponse(Exception ex, HttpStatus status) {
        RestErrorDto apiError = new RestErrorDto(status);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(RestErrorDto apiError) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}
