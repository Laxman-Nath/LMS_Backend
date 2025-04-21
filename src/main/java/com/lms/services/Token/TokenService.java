package com.lms.services.Token;

import org.springframework.security.core.Authentication;

public interface TokenService {
	String generateToken(Authentication authentication);
}
