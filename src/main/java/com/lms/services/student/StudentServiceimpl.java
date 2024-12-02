package com.lms.services.student;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dtos.student.AddStudentRequest;
import com.lms.entities.mainentities.Student;
import com.lms.exceptions.CustomException;
import com.lms.message.SuccessMessage;
import com.lms.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public SuccessMessage addStudent(AddStudentRequest student) {
		if (studentRepository.findByRollNo(student.getRollNo()) != null) {
			throw new CustomException("AS001", "Customer with this roll no already exists!");
		}
		Student s = modelMapper.map(student, Student.class);
		s.setPassword(passwordEncoder.encode(s.getPassword()));
		s.setIsEnable(true);
		s.setAddedDate(LocalDate.now());
		this.studentRepository.save(s);
		return new SuccessMessage("Student is added succesfully!");
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
//		Student oldStudent = modelMapper.map(student, Student.class);
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
		s.setUpdatedDate(LocalDate.now());
		this.studentRepository.save(s);
		return new SuccessMessage("Student with id " + studentId + " is updated succesfully!");
	}

	@Override
	public List<AddStudentRequest> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		if (students == null) {
			return null;
		}
		return students.stream().map((s) -> modelMapper.map(s, AddStudentRequest.class)).collect(Collectors.toList());
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
