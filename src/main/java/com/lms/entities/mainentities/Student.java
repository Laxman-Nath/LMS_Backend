package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lms.entities.supportingentities.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Student  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private Long rollNo;

	private LocalDate addedDate;
	private LocalDate updatedDate;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Boolean isEnable;
	private String gender;
	private LocalDate joinedDate;
	private String departmentName;
	@ManyToOne
//	@JoinColumn(name = "dept_id")
	private Department department;
	private String year;
	private String semester;
	private String profileImage;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false) 
	private Role role;
	private String password;
	private String confirmPassword;
    private Double fineAmount; 

	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<BorrowedBook> borrowedBooks=new ArrayList<>();

}
