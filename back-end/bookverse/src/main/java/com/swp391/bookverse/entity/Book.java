package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @Author huangdat
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Lob
    String description;

    @Column(nullable = false)
    Double price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    SubCategory category;

    Integer stockQuantity;
    LocalDate publishedDate;
    String image;
    Boolean active;
}