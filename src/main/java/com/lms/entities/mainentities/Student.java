package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.List;

import com.lms.entities.supportingentities.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private LocalDate addedDate;
	private LocalDate updatedDate;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
   @OneToOne
   private Department department;
   private String year;
   private String semester;
   @OneToMany
   private  List<Book> borrowedBooks;
   @OneToOne
   private Role role;
   private boolean isEnable;
}
