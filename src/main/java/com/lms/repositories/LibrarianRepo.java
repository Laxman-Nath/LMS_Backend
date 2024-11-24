package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.mainentities.Librarian;

public interface LibrarianRepo extends JpaRepository<Librarian, Long>{

}
