package com.lms.entities.mainentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "borrowed_book")
@Data
public class BorrowedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate returnedDate;
	private LocalDate borrowedDate;
	private LocalDate renewalDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="book_id")
	private Book book;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_id")
	private Student student;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
}
