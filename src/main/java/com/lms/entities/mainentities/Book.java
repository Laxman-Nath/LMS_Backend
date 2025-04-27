package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private LocalDate addedDate;
	private LocalDate updatedDate;
	private Integer quantity;
//	@OneToMany(mappedBy = "book")
//	private List<Author> authors;
	private String authorName;
	private String ISBN;
	private LocalDate publishedDate;
	private String bookImage;
//	@ManyToOne
//	@JoinColumn(name = "teacher_id")
//	private Teacher teacher;
//	@ManyToOne
//	@JoinColumn(name = "student_id")
//	private Student student;
	@OneToMany(mappedBy = "book")
	private List<BorrowedBook> borrowedBooks = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	private String year;
	private String semester;

}
