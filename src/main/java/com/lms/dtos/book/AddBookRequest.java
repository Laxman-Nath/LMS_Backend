package com.lms.dtos.book;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddBookRequest {
	private String title;

	private Integer quantity;

	private String authorName;
	private String ISBN;
	private LocalDate publishedDate;
	private String password;
	private String confirmPassword;
}
