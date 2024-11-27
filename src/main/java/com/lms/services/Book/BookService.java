package com.lms.services.Book;

import com.lms.dtos.AddBookRequest;
import java.util.List;
import com.lms.message.SuccessMessage;

public interface BookService {

	SuccessMessage addBook(AddBookRequest book);

	SuccessMessage deleteBook(Long bookId);

	SuccessMessage updateBook(Long bookId, AddBookRequest book);

	List<AddBookRequest> getAllBoooks();

	AddBookRequest getBookById(Long bookId);
}
