package com.skroflin.bookrestapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private boolean available;
}
