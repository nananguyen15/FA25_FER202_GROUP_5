package com.swp391.bookverse.dto.request;

import com.swp391.bookverse.entity.SupCategory;
import jakarta.persistence.*;
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
public class SubCategoryCreationRequest {
    Integer supCategoryId;
    String name;

    @Lob
    String description;

//    Long discountId;

    @Column(nullable = false)
    Boolean active;
}
