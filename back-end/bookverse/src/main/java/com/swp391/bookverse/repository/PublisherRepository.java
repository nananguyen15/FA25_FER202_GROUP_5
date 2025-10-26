package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author huangdat
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);
    boolean existsByNameIgnoreCase(String name);

    boolean existsByName(String name);
}