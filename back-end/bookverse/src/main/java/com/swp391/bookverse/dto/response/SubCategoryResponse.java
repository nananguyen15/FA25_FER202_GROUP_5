package com.swp391.bookverse.dto.response;

import com.swp391.bookverse.entity.SupCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE) // Set default access level for fields to private
public class SubCategoryResponse {
    Long id;
    Integer supCategoryId;

    @Column(nullable = false)
    String name;

    @Lob
    String description;

//    Long discountId;

    @Column(nullable = false)
    Boolean active;
}
