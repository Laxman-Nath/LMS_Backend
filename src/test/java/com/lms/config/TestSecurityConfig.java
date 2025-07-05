package com.lms.config;

import com.lms.config.RsaKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.jwk.RSAKey;

@TestConfiguration
public class TestSecurityConfig {

    @Value("${test.public.key}")
    private String publicKey;

    @Value("${test.private.key}")
    private String privateKey;

    @Bean
    @Primary
    public JwtEncoder jwtEncoder() {
        RSAKey rsaKey = new RSAKey.Builder(RsaKeyConverter.getPublicKeyFromString(publicKey))
                .privateKey(RsaKeyConverter.getPrivateKeyFromString(privateKey))
                .keyID("test-key")
                .build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
        return new NimbusJwtEncoder(jwkSource);
    }
}
