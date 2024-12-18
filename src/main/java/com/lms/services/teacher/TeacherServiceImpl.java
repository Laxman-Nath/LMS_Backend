package com.lms.services.teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dtos.book.AddBookRequest;
import com.lms.dtos.student.AddStudentRequest;
import com.lms.dtos.teacher.AddTeacherRequest;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.entities.supportingentities.Role;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.pagination.PaginationUtil;
import com.lms.repositories.TeacherRepository;
import com.lms.services.role.RoleService;
import com.lms.utils.PageableData;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
	private final TeacherRepository teacherRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	@Override
	public SuccessMessage addTeacher(AddTeacherRequest teacher) {
		Teacher t = modelMapper.map(teacher, Teacher.class);
		Role role = roleService.getRoleById(3l);
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		t.setRole(role);
		t.setIsEnable(true);
		t.setAddedDate(LocalDate.now());
		this.teacherRepository.save(t);
		return new SuccessMessage("Teacher is added succesfully!");
	}

	@Override
	public SuccessMessage deleteTeacher(Long teacherId) {
		if (!this.teacherRepository.existsById(teacherId)) {
			throw new CustomException("TS001", "Invalid id of teacher");
		}
		this.teacherRepository.deleteById(teacherId);
		return new SuccessMessage("Teacher with " + teacherId + " is deleted successfully!");

	}

	@Override
	public SuccessMessage updateTeacher(Long teacherId, AddTeacherRequest teacher) {
		Teacher t = this.teacherRepository.findById(teacherId)
				.orElseThrow(() -> new CustomException("TS002", "Invalid id of teacher!"));
		if (teacher.getFirstName() != null)
			t.setFirstName(teacher.getFirstName());
		if (teacher.getLastName() != null)
			t.setLastName(teacher.getLastName());

		if (teacher.getAddress() != null)
			t.setAddress(teacher.getAddress());
		if (teacher.getEmail() != null)
			t.setEmail(teacher.getEmail());

		if (teacher.getIsEnable() != null) {
			t.setIsEnable(teacher.getIsEnable());
		}
		if (teacher.getJoinedDate() != null) {
			t.setJoinedDate(teacher.getJoinedDate());
		}
		if (teacher.getGender() != null) {
			t.setGender(teacher.getGender());
		}
		t.setUpdatedDate(LocalDate.now());
		this.teacherRepository.save(t);
		return new SuccessMessage("Student with id " + teacherId + " is updated succesfully!");

	}

	@Override
	public PageableData<List<AddTeacherRequest>> getAllTeachers(Pagination pagination) {
		Pageable pageable = PaginationUtil.performPagination(pagination);
		Page<AddTeacherRequest> teachersPage = this.teacherRepository.getAllTeachers(pageable);

		return new PageableData<>(teachersPage.getContent(), teachersPage.getTotalPages(),  teachersPage.getTotalElements(),
				pagination.getPageNo());
	}

	@Override
	public AddTeacherRequest getTeacherById(Long teacherId) {
		Teacher teacher = this.teacherRepository.findById(teacherId)
				.orElseThrow(() -> new CustomException("TS004", "Invalid id of teacher!"));
		return modelMapper.map(teacher, AddTeacherRequest.class);
	}

	@Override
	public Teacher findByEmail(String email) {

		return this.teacherRepository.findByEmail(email).orElse(null);
	}

}
