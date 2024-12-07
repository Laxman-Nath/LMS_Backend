package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entities.mainentities.Book;
import com.lms.entities.mainentities.BorrowedBook;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;



public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
	boolean existsByTeacher_Id(Long teacherId);

	BorrowedBook findByTeacher_Id(Long teacherId);

	boolean existsByStudent_Id(Long studentId);

	BorrowedBook findByStudent_Id(Long studentId);

	@Query("SELECT COUNT(*) FROM BorrowedBook b WHERE b.student=:student AND b.returnedDate IS NULL")
	int countTotalBooksOfStudent(@Param("student") Student student);

	@Query("SELECT b FROM BorrowedBook b WHERE b.student=:student AND b.book=:book AND b.returnedDate IS NULL")
	BorrowedBook getBorrowedBookOfStudent(@Param("student") Student student, @Param("book") Book book);

	@Query("SELECT COUNT(*) FROM BorrowedBook b WHERE b.teacher=:teacher AND b.returnedDate IS NULL")
	int countTotalBooksOfTeacher(@Param("teacher") Teacher teacher);

	@Query("SELECT b FROM BorrowedBook b WHERE b.teacher=:teacher AND b.book=:book AND b.returnedDate IS NULL")
	BorrowedBook getBorrowedBookOfTeacher(@Param("teacher") Teacher teacher, @Param("book") Book book);

	@Query("SELECT b FROM BorrowedBook b WHERE b.renewalDate < CURRENT_DATE")
	List<BorrowedBook> getAllRenewalFailedBooks();

}
