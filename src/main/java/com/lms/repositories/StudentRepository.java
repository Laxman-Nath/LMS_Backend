package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Librarian;
import com.lms.entities.mainentities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.rollNo=:rollNo")
	Student findByRollNo(@Param("rollNo") Long rollNo);
	@Query("SELECT l FROM Student l WHERE l.email=:email")
	Optional<Student> findByEmail(@Param("email") String email);
	@Query("SELECT new com.lms.dtos.student.AddStudentRequest(s.firstName,s.lastName,s.email,s.address,s.gender,s.joinedDate,s.rollNo,s.year,s.semester,s.profileImage) FROM Student s")
	Page<AddStudentRequest> getAllStudents(Pageable pageable);
}
