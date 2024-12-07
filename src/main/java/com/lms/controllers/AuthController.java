package com.lms.controllers;

import java.nio.file.AccessDeniedException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.dtos.auth.AuthenticationSuccessUser;
import com.lms.dtos.login.LoginRequest;
import com.lms.message.AuthenticationSuccessMessage;
import com.lms.services.auth.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping(Paths.LOGIN)
	public AuthenticationSuccessMessage Login(@RequestBody LoginRequest loginRequest) throws AccessDeniedException {
		log.info("Username {}", loginRequest.getEmail());
		log.info("Password {}", loginRequest.getPassword());
		return authService.handleLogin(loginRequest);
	}

	@GetMapping(Paths.GET_AUTHENTICATED_USER)
	public AuthenticationSuccessUser getAuthenticatedUser() {
		return this.authService.getAuthenticatedUser();
	}

}
