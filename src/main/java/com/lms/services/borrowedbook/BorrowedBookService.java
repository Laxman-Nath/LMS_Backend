package com.lms.services.borrowedbook;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.lms.dtos.book.BorrowedBookResponse;
import com.lms.message.SuccessMessage;

public interface BorrowedBookService {

	SuccessMessage borrowBook(Long BookId);
	SuccessMessage returnBook(Long BookId);
	List<BorrowedBookResponse> getBorrrowedBooksOfAuthUser();
}
