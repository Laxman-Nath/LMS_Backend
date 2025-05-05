package com.lms.services.Book;

import java.util.List;

import com.lms.dtos.book.AddBookRequest;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.utils.PageableData;

public interface BookService {

	SuccessMessage addBook(AddBookRequest book);

	SuccessMessage deleteBook(Long bookId);

	SuccessMessage updateBook(Long bookId, AddBookRequest book);

	PageableData<List<AddBookRequest>> getAllBooks(Pagination pagination);

	AddBookRequest getBookById(Long bookId);
	
	PageableData<List<AddBookRequest>> getAllBooksOfAuthenticatedUser(Pagination pagination);
	
	PageableData<List<AddBookRequest>> getBooksByFilter(Pagination pagination,String departmentName,String year,String semester);
	
}
