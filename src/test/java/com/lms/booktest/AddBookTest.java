package com.lms.booktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.lms.dtos.book.AddBookRequest;
import com.lms.entities.mainentities.Book;
import com.lms.message.SuccessMessage;
import com.lms.repositories.BookRepository;
import com.lms.services.Book.BookService;
import com.lms.services.Book.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddBookTest {
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private BookRepository bookRepository;

	AddBookRequest addBookRequest;
	Book book;

	@BeforeEach
	public void setUp() {
		addBookRequest = new AddBookRequest();
		addBookRequest.setTitle("testbook");
		addBookRequest.setISBN("wert");
		addBookRequest.setAuthorName("abc");
		addBookRequest.setQuantity(10);
		addBookRequest.setPublishedDate(LocalDate.now());

		book = new Book();
		book.setTitle("testbook");
		book.setISBN("wert");
		book.setAuthorName("abc");
		book.setQuantity(10);
		book.setPublishedDate(LocalDate.now());
		book.setAddedDate(LocalDate.now());
	}

	@Test
	public void addBookTest() {

		when(modelMapper.map(addBookRequest, Book.class)).thenReturn(book);

		when(bookRepository.save(book)).thenReturn(book);

		SuccessMessage message = bookService.addBook(addBookRequest);
		assertEquals("Book is added successfully!", message.getMessage());
		verify(bookRepository, times(1)).save(book);
		verify(modelMapper, times(1)).map(addBookRequest, Book.class);
	}
}
