package com.skroflin.bookrestapi.exception;

import lombok.Getter;

@Getter
public class BadBookInputException extends RuntimeException{

    public BadBookInputException(String message) {
        super(message);
    }
}
