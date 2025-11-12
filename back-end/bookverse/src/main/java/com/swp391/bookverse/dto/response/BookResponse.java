package com.swp391.bookverse.dto.response;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @Author huangdat
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class BookResponse {
    Long id;
    String title;
    String description;
    Double price;
    Long authorId;
    Long publisherId;
    Long categoryId;
    Integer stockQuantity;
    LocalDate publishedDate;
    String image;
    Boolean active;
}
