package com.skroflin.bookrestapi.repository;

import com.skroflin.bookrestapi.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByBookId(UUID bookId);

    void deleteByBookId(UUID bookId);
}
