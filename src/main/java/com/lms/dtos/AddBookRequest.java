package com.lms.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddBookRequest {
	private String title;

	private Integer quantity;

	private String authorName;
	private String ISBN;
	private LocalDate publishedDate;
}
