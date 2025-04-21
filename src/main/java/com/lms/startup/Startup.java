//package com.lms.startup;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.lms.entities.supportingentities.Authority;
//import com.lms.entities.supportingentities.Role;
//import com.lms.repositories.AuthorityRepository;
//import com.lms.repositories.RoleRepository;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//@Transactional
//public class Startup {
//
//	private final RoleRepository roleRepository;
//	private final AuthorityRepository authorityRepository;
//
//	@PostConstruct
//	public void createRolesAndAuthorities() {
//		LocalDate currentDate = LocalDate.now();
//
//		// Authorities
//		List<String> authorityNames = Arrays.asList("ADD_BOOK", "UPDATE_BOOK", "DELETE_BOOK", "GET_BOOK_BY_ID",
//				"GET_ALL_BOOKS", "ADD_STUDENT", "UPDATE_STUDENT", "DELETE_STUDENT", "GET_STUDENT_BY_ID",
//				"GET_ALL_STUDENTS", "ADD_TEACHER", "UPDATE_TEACHER", "DELETE_TEACHER", "GET_TEACHER_BY_ID",
//				"GET_ALL_TEACHERS", "ADD_DEPT", "UPDATE_DEPT", "DELETE_DEPT", "GET_DEPT_BY_ID", "GET_ALL_DEPTS");
//
//		authorityNames.forEach(authorityName -> {
//			Authority authority = new Authority();
//			authority.setName(authorityName);
//			authority.setAddedDate(currentDate);
//			authorityRepository.save(authority);
//		});
//
//		if (roleRepository.count() == 0) {
//			Role role = new Role();
//			role.setName("ADMIN");
//			role.setAuthorities(authorityRepository.findAll());
//			roleRepository.save(role);
//		}
		
//		List<String> authorityNames = Arrays.asList( "BORROW_BOOK","RETURN_BOOK");
//
//		authorityNames.forEach(authorityName -> {
//			Authority authority = new Authority();
//			authority.setName(authorityName);
//			authority.setAddedDate(currentDate);
//			authorityRepository.save(authority);
//		});
//		String [] roleNameStrings= {"ROLE_TEACHER","ROLE_STUDENT"};

//		if (roleRepository.count() == 0) {
//		for(String r:roleNameStrings) {
//			Role role = new Role();
//			role.setName(r);
//			role.setAuthorities(authorityRepository.findAll());
//			role.setAddedDate(currentDate);
//			roleRepository.save(role);
//		}
//		}
//	}
//}
