package com.swp391.bookverse.dto.request.auth.otp;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
    // cannot be null userId
    @NotNull(message = "USER_ID_REQUIRED")
    String userId;
    @Email(message = "EMAIL_INVALID")
    String email;
    String code;
    String tokenType; // default: LOGIN
}
