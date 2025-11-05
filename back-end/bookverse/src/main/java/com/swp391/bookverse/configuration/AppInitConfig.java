package com.swp391.bookverse.configuration;

import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * @Author huangdat
 */

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {
    PasswordEncoder passwordEncoder;

    /**
     * This method is used to initialize the application when it starts.
     * @return ApplicationRunner that runs on application startup.
     */
    @Bean
    @Order(1) // Optional in when there is only one runner, but good practice to set order if multiple runners exist.
    ApplicationRunner appInitRunner(UserRepository userRepository) {
        return args -> {

            // Create an admin account if it has not existed
            if (userRepository.findByUsername("admin").isEmpty()) {
                HashSet roles = new HashSet();
                roles.add("ADMIN");

                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .email("admin@gmail.com")
                        .name("admin name")
                        .phone("0123456789")
                        .address("admin address")
                        .image("src/main/resources/static/images/avatar/admin-avatar.png")
                        .roles(roles)
                        .active(true)
                        .build();

                userRepository.save(admin);
                System.out.println("Admin account created!");
            }
        };
    }
}
