package com.lms.services.teacher;

import java.util.List;

import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Teacher;
import com.lms.message.SuccessMessage;

public interface TeacherService {
	SuccessMessage addTeacher(AddTeacherRequest teacher);

	SuccessMessage deleteTeacher(Long teacherId);

	SuccessMessage updateTeacher(Long teacherId, AddTeacherRequest teacher);

	List<AddTeacherRequest> getAllTeachers();

	AddTeacherRequest getTeacherById(Long teacherId);
	
	Teacher findByEmail(String email);
}
