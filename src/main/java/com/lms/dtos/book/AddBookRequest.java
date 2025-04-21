package com.lms.dtos.book;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBookRequest {
	private Long id;
	@NotNull
	private String title;

	private Integer quantity;

	private String authorName;
	private String ISBN;
	private LocalDate publishedDate;
	private String bookImage;
	public AddBookRequest(String title, Integer quantity, String authorName, String iSBN, LocalDate publishedDate,String bookImage) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.authorName = authorName;
		ISBN = iSBN;
		this.publishedDate = publishedDate;
		this.bookImage=bookImage;
	}
	
	public AddBookRequest(Long id,String title, Integer quantity, String authorName, String iSBN, LocalDate publishedDate,String bookImage) {
		super();
		this.id=id;
		this.title = title;
		this.quantity = quantity;
		this.authorName = authorName;
		ISBN = iSBN;
		this.publishedDate = publishedDate;
		this.bookImage=bookImage;
	}
	
}
