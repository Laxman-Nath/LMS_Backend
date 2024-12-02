package com.lms.dtos.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AddStudentRequest extends UpdateStudentRequest{
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String address;
	
	private String password;
	private String confirmPassword;
//	private Boolean isEnable;
//	private String gender;
}
