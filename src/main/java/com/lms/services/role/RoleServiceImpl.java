package com.lms.services.role;

import org.springframework.stereotype.Service;

import com.lms.entities.supportingentities.Role;
import com.lms.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public Role getRoleById(Long id) {

		return this.roleRepository.findById(id).get();
	}

}
