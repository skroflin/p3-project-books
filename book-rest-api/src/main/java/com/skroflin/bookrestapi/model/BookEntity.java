package com.skroflin.bookrestapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends BaseEntity{

    @Column(name = "book_id", nullable = false, unique = true)
    private UUID bookId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String isbn;
    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;
    @Column(nullable = false)
    private boolean available;

    @PrePersist
    protected void generateBookId() {
        if (this.bookId == null) {
            this.bookId = UUID.randomUUID();
        }
    }
}
