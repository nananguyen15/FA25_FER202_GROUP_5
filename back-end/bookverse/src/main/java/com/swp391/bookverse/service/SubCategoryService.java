package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.request.SubCategoryCreationRequest;
import com.swp391.bookverse.dto.response.SubCategoryResponse;
import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.entity.SupCategory;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.mapper.SubCategoryMapper;
import com.swp391.bookverse.repository.SubCategoryRepository;
import com.swp391.bookverse.repository.SupCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huangdat
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubCategoryService {
    SubCategoryMapper subCategoryMapper;
    SubCategoryRepository subCategoryRepository;
    SupCategoryRepository supCategoryRepository;

    public SubCategoryResponse createSubCategory(SubCategoryCreationRequest request) {
        // check if sub-category exists
        if (subCategoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.SUBCATEGORY_EXISTS);
        }

        // map request to entity and save
        SubCategory subCategory = mapToSubCategoryEntity(request);
        subCategoryRepository.save(subCategory);
        return mapToSubCategoryResponse(subCategory);
    }

    public List<SubCategoryResponse> getSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        if (subCategories.isEmpty()) {
            throw new AppException(ErrorCode.NO_SUBCATEGORIES_STORED);
        }
        return subCategories.stream()
                .map(this::mapToSubCategoryResponse)
                .toList();
    }

    public SubCategoryResponse getSubCategoryById(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new AppException(ErrorCode.SUBCATEGORY_NOT_FOUND));
        return mapToSubCategoryResponse(subCategory);
    }

    public SubCategoryResponse ubdateSubCategory(Integer subCategoryId, SubCategoryCreationRequest request) {
        SubCategory subCategory = subCategoryRepository.findById(Long.valueOf(subCategoryId))
                .orElseThrow(() -> new AppException(ErrorCode.SUBCATEGORY_NOT_FOUND));

        SupCategory supCategory = supCategoryRepository.findById(request.getSupCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.SUP_CATEGORY_NOT_FOUND));

        subCategory.setSupCategory(supCategory);
        subCategory.setName(request.getName());
        subCategory.setDescription(request.getDescription());
        subCategory.setActive(request.getActive());

        subCategoryRepository.save(subCategory);
        return mapToSubCategoryResponse(subCategory);
    }



    private SubCategory mapToSubCategoryEntity(SubCategoryCreationRequest request) {
        SupCategory supCategory = supCategoryRepository.findById(request.getSupCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.SUP_CATEGORY_NOT_FOUND));

        return SubCategory.builder()
                .supCategory(supCategory)
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .build();
    }

    private SubCategoryResponse mapToSubCategoryResponse(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .supCategoryId(subCategory.getSupCategory().getId())
                .name(subCategory.getName())
                .description(subCategory.getDescription())
                .active(subCategory.getActive())
                .build();
    }

}
