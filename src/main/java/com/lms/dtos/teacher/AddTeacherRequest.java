package com.lms.dtos.teacher;

import java.time.LocalDate;

import com.lms.dtos.student.UpdateStudentRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddTeacherRequest extends UpdateTeacherRequest{
	
	private String password;
	private String confirmPassword;
	
	public AddTeacherRequest(String firstName,String lastName,String email,String address,String gender,LocalDate joinedDate) {
		super(firstName, lastName, email, address, gender,joinedDate);
	}
	
}
