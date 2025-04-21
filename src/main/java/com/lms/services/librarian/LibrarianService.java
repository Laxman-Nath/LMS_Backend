package com.lms.services.librarian;

import com.lms.dtos.librarian.AddLibrarianRequest;
import com.lms.entities.mainentities.Librarian;
import com.lms.message.SuccessMessage;

public interface LibrarianService {
	Librarian findByEmail(String email);

	SuccessMessage addLibrarian(AddLibrarianRequest librarian);
}
