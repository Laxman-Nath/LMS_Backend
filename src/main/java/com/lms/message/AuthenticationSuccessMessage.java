package com.lms.message;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationSuccessMessage {
	private String message;
	private String token;
	private String roleName;
	
}
