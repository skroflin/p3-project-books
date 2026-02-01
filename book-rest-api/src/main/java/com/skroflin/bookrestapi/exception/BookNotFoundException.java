package com.skroflin.bookrestapi.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message) {
        super(message);
    }
}
