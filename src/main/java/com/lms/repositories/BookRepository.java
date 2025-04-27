package com.lms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.dtos.book.AddBookRequest;
import com.lms.entities.mainentities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT new com.lms.dtos.book.AddBookRequest(b.id,b.title,b.quantity,b.authorName,b.ISBN,b.publishedDate,b.bookImage) FROM Book b  ")
	Page<AddBookRequest> getAllBooks(Pageable pageable);

	@Query("SELECT new com.lms.dtos.book.AddBookRequest(b.id,b.title,b.quantity,b.authorName,b.ISBN,b.publishedDate,b.bookImage) FROM Book b WHERE b.department.id=:departmentId ")
	Page<AddBookRequest> findByDepartmentId(@Param("departmentId") Long departmentId, Pageable pageable);

	@Query("SELECT new com.lms.dtos.book.AddBookRequest(b.id,b.title,b.quantity,b.authorName,b.ISBN,b.publishedDate,b.bookImage) FROM Book b WHERE year=:year AND b.department.id=:departmentId")
    Page<AddBookRequest> findByYear(@Param("year") String year, Pageable pageable,
			@Param("departmentId") Long departmentId);

	@Query("SELECT new com.lms.dtos.book.AddBookRequest(b.id,b.title,b.quantity,b.authorName,b.ISBN,b.publishedDate,b.bookImage) FROM Book b WHERE year=:year AND semester=:semester AND b.department.id=:departmentId")
	Page<AddBookRequest> findByYearAndSemester(@Param("year") String year, @Param("semester") String semester,
			Pageable pageable,@Param("departmentId") Long departmentId);
}
