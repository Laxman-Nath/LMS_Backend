package com.lms.entities.supportingentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lms.entities.mainentities.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate addedDate;
	private LocalDate updatedDate;
	
	@ManyToMany
	private List<Authority> authorities=new ArrayList<>();
}
