package com.swp391.bookverse.entity.auth.otp;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Data
@Entity
@Table(
        name = "otp_token",
        indexes = {
                @Index(columnList = "email"),
                @Index(columnList = "email,used,expiresAt"),
                @Index(columnList = "userId,used,expiresAt")
        }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 255)
    String email;

    @Column(name = "userId", length = 36)
    String userId;

    @Column(nullable = false, length = 6)
    String code;

    @Column(nullable = false)
    Instant createdAt;

    @Column(nullable = false)
    Instant expiresAt;

    @Column(nullable = false)
    boolean used = false;

    @Column(nullable = false, length = 30)
    String tokenType;
}

