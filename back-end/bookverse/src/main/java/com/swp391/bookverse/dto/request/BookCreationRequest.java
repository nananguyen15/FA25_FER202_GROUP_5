package com.swp391.bookverse.dto.request;

import com.swp391.bookverse.entity.Author;
import com.swp391.bookverse.entity.Publisher;
import com.swp391.bookverse.entity.SubCategory;
import jakarta.persistence.*;
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
public class BookCreationRequest {

    @NotBlank(message = "Title is required")
    String title;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price;

    @NotNull(message = "Author ID is required")
    Long authorId;

    @NotNull(message = "Publisher ID is required")
    Long publisherId;

    @NotNull(message = "Category ID is required")
    Long categoryId;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    Integer stockQuantity;

    LocalDate publishedDate;

    String image;

    @NotNull(message = "Active status is required")
    Boolean active;
}
