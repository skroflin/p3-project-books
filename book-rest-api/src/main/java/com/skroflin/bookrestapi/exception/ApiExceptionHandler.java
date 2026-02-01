package com.skroflin.bookrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(ex.getMessage(), status, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {BadBookInputException.class})
    public ResponseEntity<Object> handleBadBookInput(BadBookInputException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(ex.getMessage(), status, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {InvalidIsbnException.class})
    public ResponseEntity<Object> handleBadIsbnInput(InvalidIsbnException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(ex.getMessage(), status, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, status);
    }
}
