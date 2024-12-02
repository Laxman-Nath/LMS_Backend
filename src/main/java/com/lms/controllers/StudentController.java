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
import com.lms.services.student.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;

	@PostMapping(Paths.ADD_STUDENT)
	public SuccessMessage addStudent(@RequestBody AddStudentRequest student) {
		log.info("Year {}",student.getYear());
		return this.studentService.addStudent(student);
	}

	@GetMapping(Paths.VIEW_STUDENT_BY_ID)
	public AddStudentRequest getStudentById(@RequestParam Long studentId) {
		return this.studentService.getStudentById(studentId);
	}

	@GetMapping(Paths.VIEW_ALL_STUDENTS)
	public List<AddStudentRequest> getAllStudents() {
		return this.studentService.getAllStudents();
	}

	@DeleteMapping(Paths.DELETE_STUDENT)
	public SuccessMessage deleteStudent(@RequestParam Long studentId) {
		return this.studentService.deleteStudent(studentId);
	}

	@PutMapping(Paths.UPDATE_STUDENT)
	public SuccessMessage updateStudent(@RequestBody AddStudentRequest student, @RequestParam Long studentId) {
		return this.studentService.updateStudent(studentId, student);
	}
}
