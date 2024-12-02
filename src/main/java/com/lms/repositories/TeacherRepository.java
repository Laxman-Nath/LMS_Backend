package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entities.mainentities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT l FROM Teacher l WHERE l.email=:email")
	Optional<Teacher> findByEmail(@Param("email") String email);
}
