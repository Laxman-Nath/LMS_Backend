package com.lms.config;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lms.entities.mainentities.Librarian;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.services.librarian.LibrarianService;
import com.lms.services.student.StudentService;
import com.lms.services.teacher.TeacherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
//@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {
	private final TeacherService teacherService;
	private final StudentService studentService;
	private final LibrarianService librarianService;

	public ApplicationConfig(@Lazy TeacherService teacherService, @Lazy StudentService studentService,
			@Lazy LibrarianService librarianService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.librarianService = librarianService;
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		log.info("Inside userDetailsService...");

		return username -> {
			log.info("Username {}", username);
			// Try to find the user by email in all services
			Teacher teacher = teacherService.findByEmail(username);
			if (teacher != null) {
				Set<SimpleGrantedAuthority> authorities = teacher.getRole().getAuthorities().stream()
						.map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());
				authorities.add(new SimpleGrantedAuthority(teacher.getRole().getName()));
//				log.info("Authorities{}", authorities.toString());
				return new org.springframework.security.core.userdetails.User(teacher.getEmail(), teacher.getPassword(),
						authorities);
			}

			Student student = studentService.findByEmail(username);
			if (student != null) {
				Set<SimpleGrantedAuthority> authorities = student.getRole().getAuthorities().stream()
						.map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());
				authorities.add(new SimpleGrantedAuthority(student.getRole().getName()));
//				log.info("Authorities{}", authorities.toString());
				return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(),
						authorities);
			}

			Librarian librarian = librarianService.findByEmail(username);
			if (librarian != null) {
				Set<SimpleGrantedAuthority> authorities = librarian.getRole().getAuthorities().stream()
						.map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());
				authorities.add(new SimpleGrantedAuthority(librarian.getRole().getName()));
				log.info("Librarian Authorities{}", authorities.toString());
				return new org.springframework.security.core.userdetails.User(librarian.getEmail(),
						librarian.getPassword(), authorities);
			}

			throw new UsernameNotFoundException("User with email " + username + " not found.");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
