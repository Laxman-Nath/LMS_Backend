package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lms.entities.supportingentities.Role;


import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name="teacher_table")
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
	private LocalDate joinedDate;
	@OneToOne
	private Department department;
	@OneToMany(mappedBy = "teacher")
	private List<Book> borrowedBooks=new ArrayList<>() ;
	@OneToOne
	private Role role;
	private String password;
	private String confirmPassword;
	
}
