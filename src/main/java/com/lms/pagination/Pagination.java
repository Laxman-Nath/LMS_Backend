package com.lms.pagination;

import lombok.Data;

@Data
public class Pagination {
	private int pageNo;
	private int pageSize;
	private String sortingOrder;
	private String sortParameter;
}
