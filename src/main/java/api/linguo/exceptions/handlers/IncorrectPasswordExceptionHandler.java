package api.linguo.exceptions.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.linguo.exceptions.IncorrectPasswordException;

@ControllerAdvice
public class IncorrectPasswordExceptionHandler {

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ResponseEntity<Object> exception(IncorrectPasswordException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}