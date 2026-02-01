package com.skroflin.bookrestapi.exception;

import lombok.Getter;

@Getter
public class InvalidIsbnException extends RuntimeException {

    public InvalidIsbnException(String isbn) {
        super("ISBN must be at least 10 characters long:" + " " + isbn);
    }
}
