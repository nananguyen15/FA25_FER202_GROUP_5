package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author huangdat
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    boolean existsByNameIgnoreCase(String name);
}
