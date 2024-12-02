package com.lms.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lms.dtos.login.LoginRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationHandler {
	private final AuthenticationManager authenticationManager;
	Authentication authentication;

	public Authentication handleAuthentication(LoginRequest loginRequest) {
		log.info("Inside authentication handler............");
		authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		return authentication;
	}
}
