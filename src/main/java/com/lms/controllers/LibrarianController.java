package com.lms.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.constants.Paths;
import com.lms.dtos.librarian.AddLibrarianRequest;
import com.lms.message.SuccessMessage;
import com.lms.services.librarian.LibrarianService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LibrarianController {
	private final LibrarianService librarianService;

	@PostMapping(Paths.ADD_LIBRARIAN)
	public SuccessMessage addLibrarian(@RequestBody AddLibrarianRequest librarian) {
		log.info("Name :{}",librarian.getFirstName()+" "+librarian.getLastName());
		log.info("Address: {}",librarian.getAddress());
		return this.librarianService.addLibrarian(librarian);
	}
}
