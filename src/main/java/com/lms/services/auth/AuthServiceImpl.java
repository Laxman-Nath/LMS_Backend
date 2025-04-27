package com.lms.services.auth;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lms.auth.AuthenticationHandler;
import com.lms.dtos.auth.AuthenticationSuccessUser;
import com.lms.dtos.login.LoginRequest;
import com.lms.entities.mainentities.Librarian;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.exceptions.CustomException;
import com.lms.message.AuthenticationSuccessMessage;
import com.lms.repositories.LibrarianRepo;
import com.lms.repositories.StudentRepository;
import com.lms.repositories.TeacherRepository;
import com.lms.services.Token.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

	private final AuthenticationHandler authenticationHandler;
	private final TokenService tokenService;
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;
	private final LibrarianRepo librarianRepository;

	@Override
	public AuthenticationSuccessMessage handleLogin(LoginRequest loginRequest) throws AccessDeniedException {
		log.info("Inside authentication service............");
		Authentication authentication;
		try {
			authentication = authenticationHandler.handleAuthentication(loginRequest);
		} catch (Exception exception) {
			throw new AccessDeniedException(exception.getMessage());
		}
		String token = tokenService.generateToken(authentication);
//		String userName = (String) authentication.getPrincipal();
//		System.out.println("Username:" + userName);
		AuthenticationSuccessUser user = getAuthenticatedUser();
		System.out.println("User name is:" + user.getFirstName());
		return new AuthenticationSuccessMessage("You have successfully logged in", token, user.getRoleName());
	}

	@Override
	public AuthenticationSuccessUser getAuthenticatedUser() {
		String emailString = authenticationHandler.getAuthenticatedUserName();
		if (emailString == null) {
			throw new CustomException("AS001", "No user is authenticated till now!");
		}
		System.out.println("Username is :" + emailString);
		AuthenticationSuccessUser authUser = new AuthenticationSuccessUser();
		Optional<Librarian> librarian = librarianRepository.findByEmail(emailString);
		Optional<Student> student = studentRepository.findByEmail(emailString);
		Optional<Teacher> teacher = teacherRepository.findByEmail(emailString);

		if (librarian.isPresent()) {
			authUser.setFirstName(librarian.get().getFirstName());
			authUser.setLastName(librarian.get().getLastName());
			authUser.setGender(librarian.get().getGender());
			authUser.setRoleName(librarian.get().getRole().getName());
			authUser.setEmail(librarian.get().getEmail());
		} else if (student.isPresent()) {
			authUser.setFirstName(student.get().getFirstName());
			authUser.setLastName(student.get().getLastName());
			authUser.setGender(student.get().getGender());
			authUser.setRoleName(student.get().getRole().getName());
			authUser.setEmail(student.get().getEmail());
		} else if (teacher.isPresent()) {
			authUser.setFirstName(teacher.get().getFirstName());
			authUser.setLastName(teacher.get().getLastName());
			authUser.setGender(teacher.get().getGender());
			authUser.setRoleName(teacher.get().getRole().getName());
			authUser.setEmail(teacher.get().getEmail());
		} else {
			throw new CustomException("AS002", "No user found with this email!");
		}
		return authUser;

	}

}
