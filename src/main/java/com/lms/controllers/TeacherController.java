package com.lms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.dtos.student.AddStudentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.services.teacher.TeacherService;
import com.lms.utils.PageableData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name="TeacherAPI",description = "API for managing crud operations related to teacher")
public class TeacherController {
	private final TeacherService teacherService;

	@PostMapping(Paths.ADD_TEACHER)
	public SuccessMessage addTeacher(@RequestBody AddTeacherRequest teacher) {
//		log.info("Year {}", student.getYear());
		return this.teacherService.addTeacher(teacher);
	}

	@GetMapping(Paths.VIEW_TEACHER_BY_ID)
	public AddTeacherRequest getTeacherById(@RequestParam Long id) {
		return this.teacherService.getTeacherById(id);
	}

	@GetMapping(Paths.VIEW_ALL_TEACHERS)
	public PageableData<List<AddTeacherRequest>> getAllTeachers( Pagination pagination) {
		return this.teacherService.getAllTeachers(pagination);
	}

	@DeleteMapping(Paths.DELETE_TEACHER)
	public SuccessMessage deleteTeacher(@RequestParam Long teacherId) {
		return this.teacherService.deleteTeacher(teacherId);
	}

	@PutMapping(Paths.UPDATE_TEACHER)
	public SuccessMessage updateTeacher(@RequestBody AddTeacherRequest teacher, @RequestParam Long teacherId) {
		return this.teacherService.updateTeacher(teacherId, teacher);
	}
}
