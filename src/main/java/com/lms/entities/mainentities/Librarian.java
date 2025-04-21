package com.lms.entities.mainentities;

import java.time.LocalDate;

import com.lms.entities.supportingentities.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table
public class Librarian{
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
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false) 
	private Role role;
	private String password;
	private String confirmPassword;
	private String profileImage;
}
