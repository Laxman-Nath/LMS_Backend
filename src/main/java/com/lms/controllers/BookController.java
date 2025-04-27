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
import com.lms.pagination.Pagination;
import com.lms.services.Book.BookService;
import com.lms.utils.PageableData;
import com.nimbusds.jose.Header;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "BookAPI", description = "API for managing crud operations related to book")
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
	public PageableData<List<AddBookRequest>> getAllBooks(Pagination pagination) {
//	public List<AddBookRequest> getAllBooks(Pagination pagination) {
//		bookService.getAllBooks(pagination).forEach(b -> {
//			System.out.println("Image: " + b.getBookImage());
//		});

//		System.out.println(bookService.getBookById(1254l).getBookImage());
		return bookService.getAllBooks(pagination);
	}

	@GetMapping(Paths.VIEW_BOOK_BY_ID)
	public AddBookRequest getBookById(@RequestParam Long bookId) {
		return this.bookService.getBookById(bookId);
	}

	@GetMapping(Paths.GET_BOOKS_OF_AUTHENTICATED_USER)
	public PageableData<List<AddBookRequest>> getAllBooksOfAuthenticatedUser(@RequestBody Pagination pagination) {
		System.out.println("Pagination:"+pagination);
		return this.bookService.getAllBooksOfAuthenticatedUser(pagination);
	}

}
