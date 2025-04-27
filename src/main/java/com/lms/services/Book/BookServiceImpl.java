package com.lms.services.Book;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lms.dtos.auth.AuthenticationSuccessUser;
import com.lms.dtos.book.AddBookRequest;
import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Book;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.pagination.PaginationUtil;
import com.lms.repositories.BookRepository;
import com.lms.services.auth.AuthService;
import com.lms.services.student.StudentService;
import com.lms.services.teacher.TeacherService;
import com.lms.utils.PageableData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final AuthService authService;
	private final StudentService studentService;
	private final TeacherService teacherService;

	@Override
	public SuccessMessage addBook(AddBookRequest book) {
		Book b = modelMapper.map(book, Book.class);
		b.setAddedDate(LocalDate.now());
		bookRepository.save(b);

		return new SuccessMessage("Book is added successfully!");
	}

	@Override
	public SuccessMessage deleteBook(Long bookId) {
		if (!this.bookRepository.existsById(bookId)) {
			throw new CustomException("UB001", "Invalid id of book");
		}
		this.bookRepository.deleteById(bookId);
		return new SuccessMessage("Book with " + bookId + " is deleted successfully!");
	}

	@Override
	public SuccessMessage updateBook(Long bookId, AddBookRequest book) {
		Book oldBook = modelMapper.map(book, Book.class);
		Book newBook = this.bookRepository.findById(bookId)
				.orElseThrow(() -> new CustomException("UB001", "Invalid id of book"));
		if (oldBook.getQuantity() != null && oldBook.getQuantity() != 0) {
			newBook.setQuantity(oldBook.getQuantity());
		}
		if (oldBook.getAuthorName() != null) {
			newBook.setAuthorName(oldBook.getAuthorName());
		}
		if (oldBook.getPublishedDate() != null) {
			newBook.setPublishedDate(oldBook.getPublishedDate());
		}
		if (oldBook.getTitle() != null) {
			newBook.setTitle(oldBook.getTitle());
		}
		if (oldBook.getISBN() != null) {
			newBook.setISBN(oldBook.getISBN());
		}
		if(oldBook.getBookImage()!=null) {
			newBook.setBookImage(oldBook.getBookImage());
		}

		newBook.setUpdatedDate(LocalDate.now());
		this.bookRepository.save(newBook);
		return new SuccessMessage("Book with " + bookId + " is updated successfully");
	}

	@Override
	public PageableData<List<AddBookRequest>> getAllBooks(Pagination pagination) {
//	public List<AddBookRequest> getAllBooks(Pagination pagination) {
		Pageable pageable = PaginationUtil.performPagination(pagination);
		Page<AddBookRequest> booksPage = this.bookRepository.getAllBooks(pageable);

//		return booksPage.toList();
		return new PageableData<>(booksPage.getContent(), booksPage.getTotalPages(),  booksPage.getTotalElements(),
				pagination.getPageNo());
	}

	@Override
	public AddBookRequest getBookById(Long bookId) {

		Book book = this.bookRepository.findById(bookId)
				.orElseThrow(() -> new CustomException("GB001", "Invalid id of book!"));
		return modelMapper.map(book, AddBookRequest.class);

	}

	@Override
	public PageableData<List<AddBookRequest>> getAllBooksOfAuthenticatedUser(Pagination pagination) {
		AuthenticationSuccessUser authenticationSuccessUser= authService.getAuthenticatedUser();
		if(authenticationSuccessUser==null) {
			throw new CustomException("GB002","No user is authenticated !");
		}
		Pageable pageable = PaginationUtil.performPagination(pagination);
		Page<AddBookRequest> booksPage=null;
		
		if(authenticationSuccessUser.getRoleName().equalsIgnoreCase("ROLE_STUDENT")) {
			Student student=studentService.findByEmail(authenticationSuccessUser.getEmail());
			if(student.getDepartment().getName().toLowerCase()=="csit" || student.getDepartment().getName().toLowerCase()=="bit")
			{
			  booksPage=bookRepository.findByYearAndSemester(student.getYear(), student.getSemester(), pageable, student.getDepartment().getId());	
			}
			else {
				 booksPage=bookRepository.findByYear(student.getYear(), pageable, student.getDepartment().getId());	
			}
		}
		else {
			Teacher teacher=teacherService.findByEmail(authenticationSuccessUser.getEmail());
			booksPage=bookRepository.findByDepartmentId(teacher.getDepartment().getId(), pageable);
			
		}
		return new PageableData<>(booksPage.getContent(), booksPage.getTotalPages(),  booksPage.getTotalElements(),
				pagination.getPageNo());
	}

}
