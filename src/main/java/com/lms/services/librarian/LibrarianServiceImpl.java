package com.lms.services.librarian;

import java.time.LocalDate;

import org.hibernate.validator.constraints.ISBN;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dtos.librarian.AddLibrarianRequest;
import com.lms.entities.mainentities.Librarian;
import com.lms.entities.supportingentities.Role;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.repositories.LibrarianRepo;
import com.lms.services.role.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

	private final LibrarianRepo librarianRepo;
	private final ModelMapper modelMapper;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Librarian findByEmail(String email) {

		return this.librarianRepo.findByEmail(email).orElse(null);
	}

	@Override
	public SuccessMessage addLibrarian(AddLibrarianRequest librarian) {
		Librarian librarian2 = modelMapper.map(librarian, Librarian.class);
		Role role = this.roleService.getRoleById(2l);
		if (role == null) {
			throw new CustomException("RS001", "Something went wrong on server!");
		}
		librarian2.setPassword(passwordEncoder.encode(librarian2.getPassword()));
		librarian2.setAddedDate(LocalDate.now());
		librarian2.setRole(role);
		this.librarianRepo.save(librarian2);
		return new SuccessMessage("Librarian is added successfully");
	}

}
