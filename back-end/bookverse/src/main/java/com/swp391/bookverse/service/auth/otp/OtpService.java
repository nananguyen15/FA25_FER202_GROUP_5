package com.swp391.bookverse.service.auth.otp;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.auth.otp.SendByEmailRequest;
import com.swp391.bookverse.dto.request.auth.otp.SendForUserRequest;
import com.swp391.bookverse.dto.request.auth.otp.VerifyRequest;
import com.swp391.bookverse.entity.auth.otp.OtpToken;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.repository.auth.otp.OtpTokenRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * @Author huangdat
 */
@Builder
@RequiredArgsConstructor // Generates a constructor with required arguments for final fields.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // Sets the default access level for fields to private and makes them final.
@Service
public class OtpService {
    JavaMailSender mailSender;
    OtpTokenRepository repo;
    SecureRandom rnd = new SecureRandom();
    Duration ttl = Duration.ofMinutes(5);
    int resendCooldownSec = 45;

    @Transactional
    public APIResponse<?> sendOtpByEmail(SendByEmailRequest req) {
        String normEmail = req.getEmail().trim().toLowerCase();

        repo.findTopByEmailAndUsedFalseOrderByCreatedAtDesc(normEmail).ifPresent(last -> {
            if (Instant.now().isBefore(last.getCreatedAt().plusSeconds(resendCooldownSec))) {
                throw new IllegalStateException("Please wait before requesting another code.");
            }
        });

        String code = gen6Digit();
        OtpToken t = new OtpToken();
        t.setEmail(normEmail);
        t.setUserId(null); // not linked yet
        t.setCode(code);
        t.setTokenType(req.getTokenType());
        t.setCreatedAt(Instant.now());
        t.setExpiresAt(t.getCreatedAt().plus(ttl));
        repo.save(t);

        try {
            sendEmail(normEmail, code);
        } catch (Exception e) {
            // Rollback if email sending fails
            throw new AppException(ErrorCode.EMAIL_INVALID);
        }

        return APIResponse.<Void>builder()
                .code(200)
                .message("OTP sent to email if it exists.")
                .build();

    }

//    @Transactional
//    public APIResponse<?> sendOtpForUser(SendForUserRequest req) {
//        String normEmail = req.getEmail().trim().toLowerCase();
//        Optional<OtpToken> last = repo.findTopByUserIdAndUsedFalseOrderByCreatedAtDesc(req.getUserId());
//        if (last.isPresent() && Instant.now().isBefore(last.get().getCreatedAt().plusSeconds(resendCooldownSec))) {
//            throw new IllegalStateException("Please wait before requesting another code.");
//        }
//
//        String code = gen6Digit();
//        OtpToken t = new OtpToken();
//        t.setEmail(normEmail);
//        t.setUserId(req.getUserId());
//        t.setCode(code);
//        t.setTokenType(req.getTokenType());
//        t.setCreatedAt(Instant.now());
//        t.setExpiresAt(t.getCreatedAt().plus(ttl));
//        repo.save(t);
//
//        sendEmail(normEmail, code);
//
//        return APIResponse.<Void>builder()
//                .code(200)
//                .message("OTP sent to email.")
//                .build();
//    }

    @Transactional
    public APIResponse<?> verify(VerifyRequest req) {
        String emailOrNull = req.getEmail();
        String userIdOrNull = req.getUserId();
        String code = req.getCode();
        String tokenType = req.getTokenType() == null ? "LOGIN" : req.getTokenType();

        // Fetch the token based on userId or email
        OtpToken token = (userIdOrNull != null && !userIdOrNull.isBlank())
                ? repo.findTopByUserIdAndUsedFalseOrderByCreatedAtDesc(userIdOrNull).orElse(null)
                : repo.findTopByEmailAndUsedFalseOrderByCreatedAtDesc(emailOrNull.trim().toLowerCase()).orElse(null);

        // Check if token exists
        if (token == null) {
            return APIResponse.<Void>builder()
                    .code(404)
                    .message("OTP token not found.")
                    .build();
        }

        // Validate token properties
        if (!tokenType.equals(token.getTokenType())) {
            return APIResponse.<Void>builder()
                    .code(400)
                    .message("Invalid token type.")
                    .build();
        }
        if (Instant.now().isAfter(token.getExpiresAt())) {
            return APIResponse.<Void>builder()
                    .code(400)
                    .message("OTP token has expired.")
                    .build();
        }
        if (!token.getCode().equals(code)) {
            return APIResponse.<Void>builder()
                    .code(400)
                    .message("Invalid OTP code.")
                    .build();
        }

        // Mark token as used and save
        try {
            token.setUsed(true);
            repo.save(token);
        } catch (Exception e) {
            return APIResponse.<Void>builder()
                    .code(500)
                    .message("Failed to verify OTP due to server error.")
                    .build();
        }

        // Return success response
        return APIResponse.<Void>builder()
                .code(200)
                .message("OTP verified successfully.")
                .build();
    }

    /**
     * Generate a 6-digit OTP code
     * @return String
     */
    private String gen6Digit() {
        return String.format("%06d", rnd.nextInt(1_000_000));
    }

    /**
     * Send OTP code to email
     * @param to String
     * @param code String
     */
    private void sendEmail(String to, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Your Bookverse verification code");
        msg.setText("""
        Your verification code is: %s

        It expires in 5 minutes. If you didn't request this, you can ignore this email.
        """.formatted(code));
        mailSender.send(msg);
    }
}
