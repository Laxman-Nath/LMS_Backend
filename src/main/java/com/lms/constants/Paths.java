package com.lms.constants;

public class Paths {
	public static final String BASE_URL = "/lms";
	public static final String LIBRARIAN = "/librarian";
	public static final String USER="/user";
	// book

	public static final String ADD_BOOK = BASE_URL + LIBRARIAN + "/addbook";
	public static final String VIEW_ALL_BOOKS = BASE_URL + LIBRARIAN + "/getallbooks";
	public static final String VIEW_BOOK_BY_ID = BASE_URL + LIBRARIAN + "/getbookbyid";
	public static final String UPDATE_BOOK = BASE_URL + LIBRARIAN + "/updatebook";
	public static final String DELETE_BOOK = BASE_URL + LIBRARIAN + "/deletebook";

	// student
	public static final String ADD_STUDENT = BASE_URL + LIBRARIAN + "/addstudent";
	public static final String VIEW_ALL_STUDENTS = BASE_URL + LIBRARIAN + "/getallstudents";
	public static final String VIEW_STUDENT_BY_ID = BASE_URL + LIBRARIAN + "/getstudentbyid";
	public static final String UPDATE_STUDENT = BASE_URL + LIBRARIAN + "/updatestudent";
	public static final String DELETE_STUDENT = BASE_URL + LIBRARIAN + "/deletestudent";

	// teacher
	public static final String ADD_TEACHER = BASE_URL + LIBRARIAN + "/addteacher";
	public static final String VIEW_ALL_TEACHERS = BASE_URL + LIBRARIAN + "/getallteachers";
	public static final String VIEW_TEACHER_BY_ID = BASE_URL + LIBRARIAN + "/getteacherbyid";
	public static final String UPDATE_TEACHER = BASE_URL + LIBRARIAN + "/updateteacher";
	public static final String DELETE_TEACHER = BASE_URL + LIBRARIAN + "/deleteteacher";

	// department
	public static final String ADD_DEPT = BASE_URL + LIBRARIAN + "/addept";
	public static final String VIEW_ALL_DEPTS = BASE_URL + LIBRARIAN + "/getalldepts";
	public static final String VIEW_DEPT_BY_ID = BASE_URL + LIBRARIAN + "/getdeptbyid";
	public static final String UPDATE_DEPT = BASE_URL + LIBRARIAN + "/updatedept";
	public static final String DELETE_DEPT = BASE_URL + LIBRARIAN + "/deletedept";

	// librarian
	public static final String ADD_LIBRARIAN = BASE_URL + LIBRARIAN + "/addlibrarian";

	// login
	public static final String LOGIN = BASE_URL + "/login";
	
	// borrow or return book
	public static final String BORROW_BOOK=BASE_URL+USER+"/borrowbook";
	public static final String RETURN_BOOK=BASE_URL+USER+"/returnbook";
	public static final String GET_ALL_BOOKS=BASE_URL+USER+"/getAllBooks";
	
	// get authenticated user
	public static final String GET_AUTHENTICATED_USER=BASE_URL+"/getauthenticateduser";
	
	//get books related to authenticated user
	public static final String GET_BOOKS_OF_AUTHENTICATED_USER=BASE_URL+"/profile/books";
	
}
