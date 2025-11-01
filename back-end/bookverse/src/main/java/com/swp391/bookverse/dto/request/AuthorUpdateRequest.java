package com.swp391.bookverse.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class AuthorUpdateRequest {
    String image;
    @Column(nullable = false)
    String name;
    @Lob
    String bio;
    @Column(nullable = false)
    Boolean active;
}
