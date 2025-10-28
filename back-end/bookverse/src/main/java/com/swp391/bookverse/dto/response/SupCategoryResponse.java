package com.swp391.bookverse.dto.response;

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
public class SupCategoryResponse {
    Integer id;
    String name;
    Boolean active;
}
