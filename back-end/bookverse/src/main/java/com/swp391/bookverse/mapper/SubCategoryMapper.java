package com.swp391.bookverse.mapper;

import com.swp391.bookverse.dto.response.SubCategoryResponse;
import com.swp391.bookverse.entity.SubCategory;
import org.mapstruct.Mapper;

/**
 * @Publisher huangdat
 */
@Mapper(componentModel = "spring")
public interface SubCategoryMapper {
    SubCategoryResponse toSubCategoryResponse(SubCategory subCategory);
    SubCategory toSubCategory(SubCategoryResponse response);
}
