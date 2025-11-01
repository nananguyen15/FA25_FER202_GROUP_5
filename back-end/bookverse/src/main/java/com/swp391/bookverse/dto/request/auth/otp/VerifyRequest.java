package com.swp391.bookverse.dto.request.auth.otp;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class VerifyRequest {
    String userId;
    @Email(message = "EMAIL_INVALID")
    String email;
    String code;
    String tokenType; // default: LOGIN
}
