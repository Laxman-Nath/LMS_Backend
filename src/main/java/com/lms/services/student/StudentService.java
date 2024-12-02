package com.lms.services.student;

import java.util.List;

import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Student;
import com.lms.message.SuccessMessage;

public interface StudentService {
	SuccessMessage addStudent(AddStudentRequest student);

	SuccessMessage deleteStudent(Long studentId);

	SuccessMessage updateStudent(Long studentId, AddStudentRequest student);

	List<AddStudentRequest> getAllStudents();

	AddStudentRequest getStudentById(Long studentId);
	
	Student findByEmail(String email);
}
