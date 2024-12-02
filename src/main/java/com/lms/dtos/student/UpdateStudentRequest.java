package com.lms.dtos.student;

import java.time.LocalDate;

import com.lms.dtos.baseclass.BaseClass;

import lombok.Data;

@Data
public class UpdateStudentRequest extends BaseClass{
	
	private Boolean isEnable;
	
	private LocalDate joinedDate;
	private Long rollNo;
	private String year;
	private String semester;
}
