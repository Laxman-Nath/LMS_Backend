package com.lms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entities.mainentities.Librarian;

public interface LibrarianRepo extends JpaRepository<Librarian, Long>{
	@Query("SELECT l FROM Librarian l WHERE l.email=:email")
	Optional<Librarian> findByEmail(@Param("email") String email);
}
