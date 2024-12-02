package com.lms.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.dtos.book.AddBookRequest;
import com.lms.message.SuccessMessage;
import com.lms.services.Book.BookService;
import com.nimbusds.jose.Header;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {
	private final BookService bookService;

	@PostMapping(Paths.ADD_BOOK)
	public SuccessMessage addBook(@RequestBody AddBookRequest book) {
		log.info("ISBN {}", book.getISBN());
		return bookService.addBook(book);
	}

	@DeleteMapping(Paths.DELETE_BOOK)
	public SuccessMessage deleteBook(@RequestParam Long bookId) {
		return bookService.deleteBook(bookId);
	}

	@PutMapping(Paths.UPDATE_BOOK)
	public SuccessMessage updateBook(@RequestBody AddBookRequest book, @RequestParam Long bookId) {
		return bookService.updateBook(bookId, book);
	}

	@GetMapping(Paths.VIEW_ALL_BOOKS)
	public List<AddBookRequest> getAllBooks(@RequestHeader("Authorization") String token) {
		log.info("inside book token: {}",token);
		return bookService.getAllBoooks();
	}

	@GetMapping(Paths.VIEW_BOOK_BY_ID)
	public AddBookRequest getBookById(@RequestParam Long bookId) {
		return this.bookService.getBookById(bookId);
	}

}
