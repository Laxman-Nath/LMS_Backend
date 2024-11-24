package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private LocalDate addedDate;
	private LocalDate updatedDate;
	@OneToMany(mappedBy = "book")
	private List<Author> authors;
	private String ISBN;
	private LocalDate publishedDate;
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
