package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entities.mainentities.Librarian;
import com.lms.entities.mainentities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.rollNo=:rollNo")
	Student findByRollNo(@Param("rollNo") Long rollNo);
	@Query("SELECT l FROM Student l WHERE l.email=:email")
	Optional<Student> findByEmail(@Param("email") String email);
}
