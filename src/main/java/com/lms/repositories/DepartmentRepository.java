package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.dtos.dept.AddDepartmentRequest;
import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	@Query("SELECT new com.lms.dtos.dept.AddDepartmentRequest(d.id,d.name,d.departmentHead) FROM Department d")
	Page<AddDepartmentRequest> getAllDepartments(Pageable pageable);
	
	@Query("SELECT d FROM Department d WHERE d.name=:name")
	Optional<Department> findByName(@Param("name") String name);
}
