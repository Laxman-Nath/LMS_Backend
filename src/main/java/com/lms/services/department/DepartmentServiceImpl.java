package com.lms.services.department;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lms.dtos.dept.AddDepartmentRequest;
import com.lms.dtos.student.AddStudentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Department;
import com.lms.entities.mainentities.Teacher;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.repositories.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository departmentRepository;
	private final ModelMapper modelMapper;

	@Override
	public SuccessMessage addDepartment(AddDepartmentRequest dept) {
		Department d = modelMapper.map(dept, Department.class);

		d.setAddedDate(LocalDate.now());
		this.departmentRepository.save(d);
		return new SuccessMessage("Department is added succesfully!");
	}

	@Override
	public SuccessMessage deleteDepartment(Long deptId) {
		if (!this.departmentRepository.existsById(deptId)) {
			throw new CustomException("DS001", "Invalid id of department");
		}
		this.departmentRepository.deleteById(deptId);
		return new SuccessMessage("Department with " + deptId + " is deleted successfully!");

	}

	@Override
	public SuccessMessage updateDepartment(Long deptId, AddDepartmentRequest dept) {
		Department t = this.departmentRepository.findById(deptId)
				.orElseThrow(() -> new CustomException("DS002", "Invalid id of department!"));
		if (dept.getName() != null)
			t.setName(dept.getName());
		if (dept.getDepartmentHead() != null)
			t.setDepartmentHead(dept.getDepartmentHead());

		t.setUpdatedDate(LocalDate.now());
		this.departmentRepository.save(t);
		return new SuccessMessage("Department with id " + deptId + " is updated succesfully!");
	}

	@Override
	public List<AddDepartmentRequest> getAllDepartments() {
		List<Department> depts = departmentRepository.findAll();
		if (depts == null) {
			throw new CustomException("DS003", "There are no teachers!");
		}
		return depts.stream().map((s) -> modelMapper.map(s, AddDepartmentRequest.class)).collect(Collectors.toList());
	}

	@Override
	public AddDepartmentRequest getDepartmentById(Long deptId) {
		Department dept = this.departmentRepository.findById(deptId)
				.orElseThrow(() -> new CustomException("DS004", "Invalid id of department!"));
		return modelMapper.map(dept, AddDepartmentRequest.class);
	}

}
