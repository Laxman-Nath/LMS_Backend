package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.supportingentities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
