package com.lms.services.auth;

import java.nio.file.AccessDeniedException;

import com.lms.dtos.auth.AuthenticationSuccessUser;
import com.lms.dtos.login.LoginRequest;
import com.lms.message.AuthenticationSuccessMessage;

public interface AuthService {
	AuthenticationSuccessMessage handleLogin(LoginRequest loginRequest) throws AccessDeniedException;
	AuthenticationSuccessUser getAuthenticatedUser();
}
