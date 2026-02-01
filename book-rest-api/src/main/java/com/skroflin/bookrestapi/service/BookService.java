package com.skroflin.bookrestapi.service;

import com.skroflin.bookrestapi.dto.BookRequest;
import com.skroflin.bookrestapi.dto.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {

    BookResponse getByBookId(UUID bookId);

    List<BookResponse> getAll();

    Page<BookResponse> getAll(Pageable pageable);

    void create(BookRequest request);

    void update(UUID bookId, BookRequest request);

    void delete(UUID bookId);
}
