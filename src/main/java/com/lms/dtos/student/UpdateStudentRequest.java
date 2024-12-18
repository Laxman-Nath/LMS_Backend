package com.lms.dtos.student;

import java.time.LocalDate;

import com.lms.dtos.baseclass.BaseClass;

import lombok.Data;

@Data
public class UpdateStudentRequest extends BaseClass {

	private Boolean isEnable;

	private LocalDate joinedDate;
	private Long rollNo;
	private String year;
	private String semester;

	public UpdateStudentRequest(String firstName, String lastName, String email, String address, String gender,
			LocalDate joinedDate, Long rollNo, String year, String semester) {
		super(firstName, lastName, email, address, gender);
		this.joinedDate = joinedDate;
		this.rollNo = rollNo;
		this.year = year;
		this.semester = semester;
	}
}
