package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lms.entities.supportingentities.Role;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name="teacher_table")
public class Teacher{
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
	@OneToOne
	private Department department;
	private Double fineAmount; 
	@OneToOne
	private Role role;
	private String password;
	private String confirmPassword;
	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BorrowedBook> borrowedBooks=new ArrayList<>();
	
}
