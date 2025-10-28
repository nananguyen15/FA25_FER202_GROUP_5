package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.SupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author huangdat
 */
@Repository
public interface SupCategoryRepository extends JpaRepository<SupCategory, Integer> {
    SupCategory findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    List<SupCategory> findByActiveTrue();

    int countByActiveTrue();
}
