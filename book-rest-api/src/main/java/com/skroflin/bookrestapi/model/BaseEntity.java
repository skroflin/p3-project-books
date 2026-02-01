package com.skroflin.bookrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private Instant createdAt;
    private Instant modifiedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        modifiedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        modifiedAt = Instant.now();
    }
}
