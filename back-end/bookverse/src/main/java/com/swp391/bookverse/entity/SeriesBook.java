package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SeriesBookId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeriesBook {
    @Id
    @ManyToOne
    @JoinColumn(name = "series_id")
    Series series;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
}