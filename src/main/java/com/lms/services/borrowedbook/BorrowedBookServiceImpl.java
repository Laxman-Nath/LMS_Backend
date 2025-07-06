package com.lms.services.borrowedbook;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lms.auth.AuthenticationHandler;
import com.lms.entities.mainentities.Book;
import com.lms.entities.mainentities.BorrowedBook;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.entities.mainentities.User;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.repositories.BookRepository;
import com.lms.repositories.BorrowedBookRepository;
import com.lms.repositories.StudentRepository;
import com.lms.repositories.TeacherRepository;
import com.lms.services.Book.BookService;
import com.lms.services.student.StudentService;
import com.lms.services.teacher.TeacherService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowedBookServiceImpl implements BorrowedBookService {
	private final BorrowedBookRepository borrowedBookRepository;
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final BookService bookService;
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final AuthenticationHandler authenticationHandler;

	@Override
	@Transactional
	public SuccessMessage borrowBook(Long bookId) {
		log.info("Inside borrowbook................");
		String userName = authenticationHandler.getAuthenticatedUserName();
		if (userName == null) {
			throw new CustomException("BB001", "Unauthorized!");
		}

		Book book = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("BB001", "Invalid book!"));
		if (book == null) {
			throw new CustomException("BB002", "Book not found!");
		}

		Teacher teacher = teacherService.findByEmail(userName);
		Student student = studentService.findByEmail(userName);

		if (teacher != null) {
			log.info("Inside teacher not null................");
			if (borrowedBookRepository.getBorrowedBookOfTeacher(teacher, book) != null) {
				throw new CustomException("BB005", "You have already taken this book!");
			} else {
				log.info("Inside teacher not null else part................");
				BorrowedBook borrowedBook = createBorrowedBookForTeacher(teacher, book);
				borrowedBookRepository.save(borrowedBook);
			}
		} else if (student != null) {
			log.info("Inside student not null................");
			if (student.getFineAmount() != null) {
				throw new CustomException("BB006", "Your fine is not paid yet!");
			} else if (borrowedBookRepository.countTotalBooksOfStudent(student) >= 3) {
				throw new CustomException("BB007", "You have already taken 3 books!");
			} else if (borrowedBookRepository.getBorrowedBookOfStudent(student, book) != null) {
				throw new CustomException("BB008", "You have already taken this book!");
			} else {
				log.info("Inside student not null else part................");
				BorrowedBook borrowedBook = createBorrowedBookForStudent(student, book);
				borrowedBookRepository.save(borrowedBook);
			}
		} else {
			throw new CustomException("BB009", "User is neither a teacher nor a student!");
		}

		return new SuccessMessage("Book was issued successfully! Contact the library.");
	}

	private BorrowedBook createBorrowedBookForTeacher(Teacher teacher, Book book) {
		// log.info("Inside teacher................");
		BorrowedBook borrowedBook = new BorrowedBook();
		borrowedBook.setBorrowedDate(LocalDate.now());
		borrowedBook.setRenewalDate(LocalDate.now().plusDays(15));
		borrowedBook.setTeacher(teacher);
		bookRepository.save(book);
		borrowedBook.setBook(book);
		teacher.getBorrowedBooks().add(borrowedBook);
		book.getBorrowedBooks().add(borrowedBook);
		teacherRepository.save(teacher);

		return borrowedBook;
	}

	private BorrowedBook createBorrowedBookForStudent(Student student, Book book) {
		BorrowedBook borrowedBook = new BorrowedBook();
		borrowedBook.setBorrowedDate(LocalDate.now());
		borrowedBook.setStudent(student);
		bookRepository.save(book);
		borrowedBook.setBook(book);
		borrowedBook.setRenewalDate(LocalDate.now().plusDays(15));
		book.getBorrowedBooks().add(borrowedBook);
		student.getBorrowedBooks().add(borrowedBook);

		studentRepository.save(student);

		return borrowedBook;
	}

	@Override
	public SuccessMessage returnBook(Long bookId) {
		String userName = authenticationHandler.getAuthenticatedUserName();
		if (userName == null) {
			throw new CustomException("BB0010", "Unauthorized!");
		}

		// Retrieve the book, handle case where book is not found
		Book book = modelMapper.map(bookService.getBookById(bookId), Book.class);
		if (book == null) {
			throw new CustomException("BB0011", "Book not found!");
		}

		// Determine if the user is a teacher or student
		Teacher teacher = teacherService.findByEmail(userName);
		Student student = studentService.findByEmail(userName);

		if (teacher != null) {
			// Handle teacher-specific return logic

			BorrowedBook borrowedBook = borrowedBookRepository.getBorrowedBookOfTeacher(teacher, book);
			if (borrowedBook != null) {
				borrowedBook.setReturnedDate(LocalDate.now());
				borrowedBookRepository.save(borrowedBook);
			} else {
				throw new CustomException("BB005", "You have not borrowed this book!");
			}
		} else if (student != null) {
			// Handle student-specific return logic
			if (student.getFineAmount() != null) {
				throw new CustomException("BB0013",
						"Your fine is not paid yet! Please contact the library and return the book there.");
			} else {
				BorrowedBook borrowedBook = borrowedBookRepository.getBorrowedBookOfStudent(student, book);
				if (borrowedBook != null) {
					borrowedBook.setReturnedDate(LocalDate.now());
					borrowedBookRepository.save(borrowedBook);
				} else {
					throw new CustomException("BB0014", "You have not borrowed this book!");
				}
			}
		} else {
			throw new CustomException("BB0015", "User is neither a teacher nor a student!");
		}

		return new SuccessMessage("Book was returned successfully!");
	}

}
