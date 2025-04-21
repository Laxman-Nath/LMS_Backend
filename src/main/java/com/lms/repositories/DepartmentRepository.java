package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.mainentities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
