package com.lms.services.student;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Department;
import com.lms.entities.mainentities.Student;
import com.lms.entities.supportingentities.Role;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.pagination.Pagination;
import com.lms.pagination.PaginationUtil;
import com.lms.repositories.StudentRepository;
import com.lms.services.QueueService.EmailProducer;
import com.lms.services.department.DepartmentService;
import com.lms.services.role.RoleService;
import com.lms.utils.PageableData;
import com.lms.utils.PasswordGenerator;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PasswordGenerator passwordGenerator;
	@Autowired
	private @Lazy EmailProducer emailProducer;

	@Override
	public SuccessMessage addStudent(AddStudentRequest student) {
		Role role = this.roleService.getRoleById(4L);

		if (studentRepository.findByRollNo(student.getRollNo()) != null) {
			throw new CustomException("AS001", "Student with this roll no already exists!");
		}

		Student s = modelMapper.map(student, Student.class);
		Department dept = departmentService.findByDepartmentName(student.getDepartmentName());

		String rawPwd = passwordGenerator.generateRandomPassword();
		s.setPassword(passwordEncoder.encode(rawPwd));
		System.out.println("Random password generated is:" + rawPwd);
		s.setDepartment(dept);
		s.setRole(role);
		s.setIsEnable(true);
		s.setAddedDate(LocalDate.now());

		studentRepository.save(s);

		try {
		emailProducer.sendWelcomeEmail(s.getFirstName() + " " + s.getLastName(),
		rawPwd, s.getEmail());
		} catch (MessagingException ex) {

		throw new CustomException("EMAIL001",
		"Failed to send welcome email to " + s.getEmail() + ": " + ex.getMessage());
		}

		return new SuccessMessage("Student is added successfully!");
	}

	@Override
	public SuccessMessage deleteStudent(Long studentId) {
		if (!this.studentRepository.existsById(studentId)) {
			throw new CustomException("DS001", "Invalid id of book");
		}
		this.studentRepository.deleteById(studentId);
		return new SuccessMessage("Student with " + studentId + " is deleted successfully!");

	}

	@Override
	public SuccessMessage updateStudent(Long studentId, AddStudentRequest student) {
		// Student oldStudent = modelMapper.map(student, Student.class);
		Student s = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new CustomException("US001", "Invalid id of student"));
		if (student.getFirstName() != null)
			s.setFirstName(student.getFirstName());
		if (student.getLastName() != null)
			s.setLastName(student.getLastName());
		if (student.getRollNo() != null)
			s.setRollNo(student.getRollNo());
		if (student.getAddress() != null)
			s.setAddress(student.getAddress());
		if (student.getEmail() != null)
			s.setEmail(student.getEmail());
		if (student.getSemester() != null)
			s.setSemester(student.getSemester());
		if (student.getYear() != null)
			s.setYear(student.getYear());
		if (student.getIsEnable() != null) {
			s.setIsEnable(student.getIsEnable());
		}
		if (student.getJoinedDate() != null) {
			s.setJoinedDate(student.getJoinedDate());
		}
		if (student.getGender() != null) {
			s.setGender(student.getGender());
		}
		if (student.getProfileImage() != null) {
			s.setProfileImage(student.getProfileImage());
		}
		s.setUpdatedDate(LocalDate.now());
		this.studentRepository.save(s);
		return new SuccessMessage("Student with id " + studentId + " is updated succesfully!");
	}

	@Override
	public PageableData<List<AddStudentRequest>> getAllStudents(Pagination pagination) {
		Page<AddStudentRequest> studentsPage = this.studentRepository
				.getAllStudents(PaginationUtil.performPagination(pagination));
		return new PageableData<>(studentsPage.getContent(), studentsPage.getTotalPages(),
				studentsPage.getTotalElements(), pagination.getPageNo());
	}

	@Override
	public AddStudentRequest getStudentById(Long studentId) {

		Student student = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new CustomException("GS001", "Invalid id of student!"));
		return modelMapper.map(student, AddStudentRequest.class);
	}

	@Override
	public Student findByEmail(String email) {

		return this.studentRepository.findByEmail(email).orElse(null);
	}

}
