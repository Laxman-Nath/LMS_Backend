package com.lms.services.borrowedbook;

import org.springframework.web.bind.annotation.RequestParam;

import com.lms.message.SuccessMessage;

public interface BorrowedBookService {

	SuccessMessage borrowBook(Long BookId);
	SuccessMessage returnBook(Long BookId);
}
