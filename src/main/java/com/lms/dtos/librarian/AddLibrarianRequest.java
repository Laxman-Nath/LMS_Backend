package com.lms.dtos.librarian;

import lombok.Data;

@Data
public class AddLibrarianRequest extends UpdateLibrarianRequest {

	private String password;
	private String confirmPassword;
}
