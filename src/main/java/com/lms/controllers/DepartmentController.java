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
import com.lms.dtos.dept.AddDepartmentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.message.SuccessMessage;
import com.lms.services.department.DepartmentService;
import com.lms.services.teacher.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService deptService;

	@PostMapping(Paths.ADD_DEPT)
	public SuccessMessage addDepartment(@RequestBody AddDepartmentRequest dept) {
//		log.info("Year {}", student.getYear());
		return this.deptService.addDepartment(dept);
	}

	@GetMapping(Paths.VIEW_DEPT_BY_ID)
	public AddDepartmentRequest getDepartmentById(@RequestParam Long deptId) {
		return this.deptService.getDepartmentById(deptId);
	}

	@GetMapping(Paths.VIEW_ALL_DEPTS)
	public List<AddDepartmentRequest> getAllDepartments() {
		return this.deptService.getAllDepartments();
	}

	@DeleteMapping(Paths.DELETE_DEPT)
	public SuccessMessage deleteDepartment(@RequestParam Long deptId) {
		return this.deptService.deleteDepartment(deptId);
	}

	@PutMapping(Paths.UPDATE_DEPT)
	public SuccessMessage updateDepartment(@RequestBody AddDepartmentRequest dept, @RequestParam Long deptId) {
		return this.deptService.updateDepartment(deptId, dept);
	}
}
