package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT l FROM Teacher l WHERE l.email=:email")
	Optional<Teacher> findByEmail(@Param("email") String email);
	
	@Query("SELECT new com.lms.dtos.teacher.AddTeacherRequest(t.firstName,t.lastName,t.email,t.address,t.gender,t.joinedDate,t.profileImage) FROM Teacher t")
	Page<AddTeacherRequest> getAllTeachers(Pageable pageable);
}
