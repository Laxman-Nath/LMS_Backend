package com.lms.dtos.teacher;

import java.time.LocalDate;

import com.lms.dtos.baseclass.BaseClass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateTeacherRequest extends BaseClass {

	private Boolean isEnable;

	private LocalDate joinedDate;
	public UpdateTeacherRequest(String firstName,String lastName,String email,String address,String gender,LocalDate joinedDate) {
		super(firstName,lastName,email,address,gender);
		this.joinedDate=joinedDate;
	}
}
