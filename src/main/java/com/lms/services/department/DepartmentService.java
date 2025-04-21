package com.lms.services.department;

import java.util.List;

import com.lms.dtos.dept.AddDepartmentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Department;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.utils.PageableData;

public interface DepartmentService {
	SuccessMessage addDepartment(AddDepartmentRequest dept);

	SuccessMessage deleteDepartment(Long deptId);

	SuccessMessage updateDepartment(Long deptId, AddDepartmentRequest Dept);

	PageableData<List<AddDepartmentRequest>> getAllDepartments(Pagination pagination);

	AddDepartmentRequest getDepartmentById(Long deptId);

	Department findByDepartmentName(String name);
}
