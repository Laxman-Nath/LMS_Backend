package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.mainentities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
