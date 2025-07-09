package com.lms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.dtos.book.BorrowedBookResponse;
import com.lms.message.SuccessMessage;
import com.lms.services.borrowedbook.BorrowedBookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserAPI", description = "API for managing crud operations related to users")
public class UserController {
	private final BorrowedBookService borrowedBookService;

	@PostMapping(Paths.BORROW_BOOK)
	public SuccessMessage borrowBook(@RequestParam Long bookId) {
		{
			System.out.println("Inside borrowed book:" + bookId);
			return this.borrowedBookService.borrowBook(bookId);
		}

	}

	@PutMapping(Paths.RETURN_BOOK)
	public SuccessMessage returnBook(@RequestParam Long bookId) {
		return this.borrowedBookService.returnBook(bookId);
	}

	@GetMapping(Paths.GET_ALL_BOOKS_OF_AUTH_USER)
	public List<BorrowedBookResponse> getBorrrowedBooksOfAuthenticatedUser() {
		System.out.println("Request reached");
		return this.borrowedBookService.getBorrrowedBooksOfAuthUser();
	}
}
