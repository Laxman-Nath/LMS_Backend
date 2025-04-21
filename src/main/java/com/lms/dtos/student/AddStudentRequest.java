package com.lms.dtos.student;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AddStudentRequest extends UpdateStudentRequest{
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String address;
	private Long id;
	private String password;
	private String confirmPassword;
	
	public AddStudentRequest(Long id,String firstName, String lastName, String email, String address, String gender,
			LocalDate joinedDate, Long rollNo, String year, String semester,String profileImage,String departmentName) {
		
		super(firstName, lastName, email, address, gender,joinedDate,rollNo,year,semester,profileImage, departmentName);
		this.id=id;
		
	}
//	private Boolean isEnable;
//	private String gender;
}
