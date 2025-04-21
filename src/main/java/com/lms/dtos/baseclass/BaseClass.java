package com.lms.dtos.baseclass;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseClass {
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String gender;
	private String profileImage;
	private String departmentName;

	public BaseClass(String firstName, String lastName, String email, String address, String gender,String profileImage
			,String departmentName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.profileImage=profileImage;
		this.departmentName=departmentName;
	}
}
