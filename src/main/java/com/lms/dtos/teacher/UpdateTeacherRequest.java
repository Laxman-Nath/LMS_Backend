package com.lms.dtos.teacher;

import java.time.LocalDate;

import com.lms.dtos.baseclass.BaseClass;

import lombok.Data;

@Data
public class UpdateTeacherRequest extends BaseClass {

	private Boolean isEnable;

	private LocalDate joinedDate;
}
