package com.swp391.bookverse.dto.request;

import com.swp391.bookverse.entity.SupCategory;
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
public class SubCategoryUpdateRequest {
    String supCategoryId;
    String name;
    @Lob
    String description;

//    Long discountId;
}
