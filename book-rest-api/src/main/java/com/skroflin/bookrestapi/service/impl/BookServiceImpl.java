package com.skroflin.bookrestapi.service.impl;

import com.skroflin.bookrestapi.dto.BookRequest;
import com.skroflin.bookrestapi.dto.BookResponse;
import com.skroflin.bookrestapi.exception.BadBookInputException;
import com.skroflin.bookrestapi.exception.BookNotFoundException;
import com.skroflin.bookrestapi.exception.InvalidIsbnException;
import com.skroflin.bookrestapi.mapper.BookMapper;
import com.skroflin.bookrestapi.model.BookEntity;
import com.skroflin.bookrestapi.repository.BookRepository;
import com.skroflin.bookrestapi.service.BookService;
import jakarta.transaction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public BookResponse getByBookId(UUID bookId) {
        BookEntity entity = bookRepository.findByBookId(bookId)
                .orElseThrow(
                        () -> new BookNotFoundException("Book with id" + " " + bookId + " " + "doesn't exist!")
                );
        return bookMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAll() {
        List<BookEntity> entities = bookRepository.findAll();
        return bookMapper.toResponseList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> getAll(Pageable pageable) {
        Page<BookEntity> page = bookRepository.findAll(pageable);
        return page.map(bookMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public void create(BookRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw  new BadBookInputException("Book title can't be empty.");
        } else if (request.getAuthor() == null || request.getAuthor().isBlank()) {
            throw  new BadBookInputException("Book author can't be empty.");
        } else if (request.getIsbn() == null || request.getIsbn().isBlank() || request.getIsbn() != null && request.getIsbn().length() < 10) {
            throw new InvalidIsbnException(request.getIsbn());
        }

        BookEntity entity = bookMapper.toEntity(request);
        bookRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(UUID bookId, BookRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw  new BadBookInputException("Book title can't be empty.");
        } else if (request.getAuthor() == null || request.getAuthor().isBlank()) {
            throw  new BadBookInputException("Book author can't be empty.");
        } else if (request.getIsbn() == null || request.getIsbn().isBlank() || request.getIsbn() != null && request.getIsbn().length() < 10) {
            throw new InvalidIsbnException(request.getIsbn());
        }

        BookEntity entity = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id" + " " + bookId + " " + "not found."));

        bookMapper.updateEntityFromRequest(request, entity);
        bookRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(UUID bookId) {
        BookEntity entity = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id" + " " + bookId + " " + "not found."));

        bookRepository.delete(entity);
    }
}
