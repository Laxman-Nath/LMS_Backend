package com.lms.services.auth;

import java.nio.file.AccessDeniedException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lms.auth.AuthenticationHandler;
import com.lms.dtos.login.LoginRequest;
import com.lms.message.AuthenticationSuccessMessage;
import com.lms.services.Token.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

	private final AuthenticationHandler authenticationHandler;
	private final TokenService tokenService;

	@Override
	public AuthenticationSuccessMessage handleLogin(LoginRequest loginRequest) throws AccessDeniedException {
		log.info("Inside authentication service............");
		Authentication authentication;
		try {
			authentication = authenticationHandler.handleAuthentication(loginRequest);
		} catch (Exception exception) {
			throw new AccessDeniedException(exception.getMessage());
		}
		String token = tokenService.generateToken(authentication);
		return new AuthenticationSuccessMessage("You have successfully logged in", token);
	}

}
