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
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.services.student.StudentService;
import com.lms.utils.PageableData;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name="StudentAPI",description = "API for managing crud operations related to students")
public class StudentController {
	private final StudentService studentService;

	@PostMapping(Paths.ADD_STUDENT)
	public SuccessMessage addStudent(@RequestBody AddStudentRequest student) throws MessagingException {
		log.info("Year {}",student.getYear());
		return this.studentService.addStudent(student);
	}

	@GetMapping(Paths.VIEW_STUDENT_BY_ID)
	public AddStudentRequest getStudentById(@RequestParam Long studentId) {
		return this.studentService.getStudentById(studentId);
	}

	@GetMapping(Paths.VIEW_ALL_STUDENTS)
	public PageableData<List<AddStudentRequest>> getAllStudents(Pagination pagination) {
		System.out.println("inside get all students");
		log.info("inside get all students");
		return this.studentService.getAllStudents(pagination);
	}

	@DeleteMapping(Paths.DELETE_STUDENT)
	public SuccessMessage deleteStudent(@RequestParam Long id) {
		return this.studentService.deleteStudent(id);
	}

	@PutMapping(Paths.UPDATE_STUDENT)
	public SuccessMessage updateStudent(@RequestBody AddStudentRequest student, @RequestParam Long studentId) {
		return this.studentService.updateStudent(studentId, student);
	}
}
