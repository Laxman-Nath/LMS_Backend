package com.lms.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageableData<T> {
	private T data;
	private int pageNumber;
	private int totalPage;
	private long totalData;
	
	 public PageableData(T data, int totalPage, long totalData, int pageNumber) {
	        this.data = data;
	        this.totalPage = totalPage;
	        this.totalData = totalData;
	        this.pageNumber = pageNumber;
	    }
}
