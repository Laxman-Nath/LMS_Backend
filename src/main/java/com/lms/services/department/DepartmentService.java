package com.lms.services.department;

import java.util.List;

import com.lms.dtos.dept.AddDepartmentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.message.SuccessMessage;

public interface DepartmentService {
	SuccessMessage addDepartment(AddDepartmentRequest dept);

	SuccessMessage deleteDepartment(Long deptId);

	SuccessMessage updateDepartment(Long deptId, AddDepartmentRequest Dept);

	List<AddDepartmentRequest> getAllDepartments();

	AddDepartmentRequest getDepartmentById(Long deptId);
}
