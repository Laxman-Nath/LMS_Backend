package com.lms.entities.mainentities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String name;
	private LocalDate addedDate;
	private LocalDate updatedDate;
   private String bio;
   @ManyToOne()
   @JoinColumn(name="book_id",nullable = false)
   private Book book;
}
