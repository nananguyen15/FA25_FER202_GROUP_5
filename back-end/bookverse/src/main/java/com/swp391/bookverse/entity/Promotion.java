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
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    @Column(nullable = false)
    String content;

    Integer percentage;
    @Column(nullable = false)
    String type;
    @Column(nullable = false)
    LocalDate promotionDay;
}