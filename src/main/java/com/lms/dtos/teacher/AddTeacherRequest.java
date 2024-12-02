package com.lms.dtos.teacher;

import java.time.LocalDate;

import com.lms.dtos.student.UpdateStudentRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddTeacherRequest extends UpdateStudentRequest{
	
	private String password;
	private String confirmPassword;
}
