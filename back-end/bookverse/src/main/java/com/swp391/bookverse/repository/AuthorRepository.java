package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author huangdat
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Find an author by their name
    Author findByName(String name);
    // Check if an author exists by their name
    boolean existsByName(String name);
    // Find authors whose names contain a specific keyword (case-insensitive)
    List<Author> findByNameContainingIgnoreCase(String keyword);

    int countByActiveTrue();

    List<Author> findByActiveTrue();

    int countByActiveFalse();

    List<Author> findByActiveFalse();
}