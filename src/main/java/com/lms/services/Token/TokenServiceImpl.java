package com.lms.services.Token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	private final JwtEncoder encoder;

	@Override
	public String generateToken(Authentication authentication) {
	    Instant now = Instant.now();

	    // Collect roles starting with "ROLE_" and separate them for the roles claim
	    String roles = authentication.getAuthorities().stream()
	            .map(authority -> authority.getAuthority())
	            .filter(role -> role.startsWith("ROLE_"))
	            .collect(Collectors.joining(" "));

	    // Collect scopes, which are the authorities not starting with "ROLE_"
	    String scopes = authentication.getAuthorities().stream()
	            .map(authority -> authority.getAuthority())
	            .filter(scope -> !scope.startsWith("ROLE_"))
	            .collect(Collectors.joining(" "));

	    // Create JWT claims, including roles and scopes
	    JwtClaimsSet claims = JwtClaimsSet.builder()
	            .issuer("self")
	            .issuedAt(now)
	            .expiresAt(now.plus(10, ChronoUnit.HOURS))
	            .subject(authentication.getName())
	            .claim("roles", roles)  // Add roles to the token claim
	            .claim("scope", scopes) // Add scopes to the token claim
	            .build();

	    // Encode and return the token
	    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}


}
