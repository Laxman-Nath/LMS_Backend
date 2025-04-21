package com.lms.services.teacher;

import java.util.List;

import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Teacher;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.utils.PageableData;

public interface TeacherService {
	SuccessMessage addTeacher(AddTeacherRequest teacher);

	SuccessMessage deleteTeacher(Long teacherId);

	SuccessMessage updateTeacher(Long teacherId, AddTeacherRequest teacher);

	PageableData<List<AddTeacherRequest>> getAllTeachers(Pagination pagination);

	AddTeacherRequest getTeacherById(Long teacherId);
	
	Teacher findByEmail(String email);
}
