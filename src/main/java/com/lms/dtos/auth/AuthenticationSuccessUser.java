package com.lms.dtos.auth;

import lombok.Data;

@Data
public class AuthenticationSuccessUser {
	private String firstName;
	private String lastName;
	private String gender;
	private String roleName;
}
