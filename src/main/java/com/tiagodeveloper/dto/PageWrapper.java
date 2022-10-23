package com.tiagodeveloper.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<T> content = new ArrayList<>();
	private Integer size;
	private Integer number;
	private Integer numberOfElements;
	private Integer totalElements;

}
