package com.swp391.bookverse.dto.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

/**
 * @Author huangdat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE) // Set default access level for fields to private
public class UserResponse {
    String id;
    String username;
    String email;
    String name;
    String phone;
    String address;
    String image;
    boolean active;

    Set<String> roles; // Set of roles assigned to the user, e.g., "USER", "ADMIN", "MODERATOR", etc.
}
