package com.lms.startup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.entities.mainentities.BorrowedBook;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.repositories.BorrowedBookRepository;
import com.lms.repositories.StudentRepository;
import com.lms.repositories.TeacherRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FineClass {

	private static final Logger logger = LoggerFactory.getLogger(FineClass.class);

	private final BorrowedBookRepository borrowedBookRepository;
	
	private final StudentRepository studentRepository;

	@PostConstruct
	public void calculateFine() {
		List<BorrowedBook> renewFailedBooksList = borrowedBookRepository.getAllRenewalFailedBooks();
		if (renewFailedBooksList != null && !renewFailedBooksList.isEmpty()) {
			for (BorrowedBook b : renewFailedBooksList) {

				Student student = b.getStudent();
				if (student != null) {

					student.setFineAmount(student.getFineAmount() + 15.0);
					student.setIsEnable(false);
					studentRepository.save(student);
					logger.info("Applied fine to student {}. New fine amount: {}", student.getId(),
							student.getFineAmount());
				} else {
					logger.warn("BorrowedBook {} has no associated  student.", b.getId());
				}
			}
		} else {
			logger.info("No renewal failed books found.");
		}
	}
}
