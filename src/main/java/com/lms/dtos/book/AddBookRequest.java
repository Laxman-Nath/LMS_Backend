package com.lms.dtos.book;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBookRequest {
	private String title;

	private Integer quantity;

	private String authorName;
	private String ISBN;
	private LocalDate publishedDate;
	public AddBookRequest(String title, Integer quantity, String authorName, String iSBN, LocalDate publishedDate) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.authorName = authorName;
		ISBN = iSBN;
		this.publishedDate = publishedDate;
	}
	
}
