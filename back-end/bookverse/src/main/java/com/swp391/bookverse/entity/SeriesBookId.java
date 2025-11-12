package com.swp391.bookverse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author huangdat
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesBookId implements Serializable {
    Long series;
    Long book;
}