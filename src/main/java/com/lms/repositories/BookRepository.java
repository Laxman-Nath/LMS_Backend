package com.lms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.dtos.book.AddBookRequest;
import com.lms.entities.mainentities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
@Query("SELECT new com.lms.dtos.book.AddBookRequest(b.title,b.quantity,b.authorName,b.ISBN,b.publishedDate) FROM Book b ")	
Page<AddBookRequest> getAllBooks(Pageable pageable);
}
