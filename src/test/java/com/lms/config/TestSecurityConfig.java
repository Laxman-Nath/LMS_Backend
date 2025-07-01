package com.lms.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    public JwtEncoder jwtEncoder() {
        return mock(JwtEncoder.class); // fake JwtEncoder for tests
    }
}
