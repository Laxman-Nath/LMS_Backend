package com.lms.dtos.dept;



import lombok.Data;

@Data
public class AddDepartmentRequest {
	private Long id;
	private String name;
	
	private String departmentHead;
	
	public AddDepartmentRequest(Long id,String name,String departmentHead) {
		this.id=id;
		this.name=name;
		this.departmentHead=departmentHead;
	}
	
}
