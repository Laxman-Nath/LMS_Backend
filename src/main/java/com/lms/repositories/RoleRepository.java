package com.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.supportingentities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
