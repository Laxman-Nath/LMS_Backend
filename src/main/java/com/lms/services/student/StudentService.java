package com.lms.services.student;

import java.util.List;

import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Student;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.utils.PageableData;

public interface StudentService {
	SuccessMessage addStudent(AddStudentRequest student);

	SuccessMessage deleteStudent(Long studentId);

	SuccessMessage updateStudent(Long studentId, AddStudentRequest student);

	PageableData<List<AddStudentRequest>> getAllStudents(Pagination pagination);

	AddStudentRequest getStudentById(Long studentId);
	
	Student findByEmail(String email);
}
