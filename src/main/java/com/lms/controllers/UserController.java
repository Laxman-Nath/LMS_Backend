package com.lms.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.message.SuccessMessage;
import com.lms.services.borrowedbook.BorrowedBookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name="UserAPI",description = "API for managing crud operations related to users")
public class UserController {
	private final BorrowedBookService borrowedBookService;

	@PostMapping(Paths.BORROW_BOOK)
	public SuccessMessage borrowBook(@RequestParam Long bookId) {
		{
			return this.borrowedBookService.borrowBook(bookId);
		}

	}

	@PutMapping(Paths.RETURN_BOOK)
	public SuccessMessage returnBook(@RequestParam Long bookId) {
		return this.borrowedBookService.returnBook(bookId);
	}
}
