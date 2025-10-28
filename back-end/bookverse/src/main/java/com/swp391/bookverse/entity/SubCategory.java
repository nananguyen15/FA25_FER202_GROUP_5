package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "sup_cat_id")
    SupCategory supCategory;

    @Column(nullable = false)
    String name;

    @Lob
    String description;

//    Long discountId;

    @Column(nullable = false)
    Boolean active;
}