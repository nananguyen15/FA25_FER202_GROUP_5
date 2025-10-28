package com.swp391.bookverse.mapper;

import com.swp391.bookverse.dto.request.SupCategoryCreationRequest;
import com.swp391.bookverse.dto.response.SupCategoryResponse;
import com.swp391.bookverse.entity.SupCategory;
import org.mapstruct.Mapper;

/**
 * @Publisher huangdat
 */
@Mapper(componentModel = "spring")
public interface SupCategoryMapper{
    SupCategoryResponse toSupCategoryResponse(SupCategory supCategory);
    SupCategory toSupCategory(SupCategoryCreationRequest request);
}
