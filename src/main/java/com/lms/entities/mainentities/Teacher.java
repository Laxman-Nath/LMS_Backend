package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lms.entities.supportingentities.Role;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teacher_table")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate addedDate;
	private LocalDate updatedDate;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean isEnable;
	private String gender;
	private String address;
	private String profileImage;
	private LocalDate joinedDate;
	private String departmentName;
	@ManyToOne
	private Department department;
	private Double fineAmount;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	private String password;
	private String confirmPassword;
	@OneToMany(mappedBy = "teacher",  orphanRemoval = true)
	@JsonManagedReference
	private List<BorrowedBook> borrowedBooks = new ArrayList<>();

}
