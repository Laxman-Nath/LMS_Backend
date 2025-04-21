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
import com.lms.pagination.Pagination;
import com.lms.services.department.DepartmentService;
import com.lms.services.teacher.TeacherService;
import com.lms.utils.PageableData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name="DepartmentAPI",description = "API for managing crud operations related to department")
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
	public PageableData<List<AddDepartmentRequest>> getAllDepartments(Pagination pagination) {
		return this.deptService.getAllDepartments(pagination);
	}

	@DeleteMapping(Paths.DELETE_DEPT)
	public SuccessMessage deleteDepartment(@RequestParam Long departmentId) {
		return this.deptService.deleteDepartment(departmentId);
	}

	@PutMapping(Paths.UPDATE_DEPT)
	public SuccessMessage updateDepartment(@RequestBody AddDepartmentRequest dept, @RequestParam Long departmentId) {
		System.out.println("inside update method of controller:"+departmentId);
		return this.deptService.updateDepartment(departmentId, dept);
	}
}
