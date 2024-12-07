package com.lms.entities.mainentities;

import java.time.LocalDate;

import com.lms.entities.supportingentities.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table
public class Librarian implements User{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate addedDate;
	private LocalDate updatedDate;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String gender;
	@OneToOne
	private Role role;
	private String password;
	private String confirmPassword;
}
