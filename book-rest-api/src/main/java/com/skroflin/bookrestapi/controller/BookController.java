package com.skroflin.bookrestapi.controller;

import com.skroflin.bookrestapi.dto.BookRequest;
import com.skroflin.bookrestapi.dto.BookResponse;
import com.skroflin.bookrestapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/page")
    public Page<BookResponse> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return bookService.getAll(pageable);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBookById(@PathVariable UUID bookId) {
        return bookService.getByBookId(bookId);
    }

    @PostMapping
    public void createBook(@RequestBody BookRequest request) {
        bookService.create(request);
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable UUID bookId, @RequestBody BookRequest request) {
        bookService.update(bookId, request);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable UUID bookId) {
        bookService.delete(bookId);
    }
}
