package com.donationmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ResourceNotFoundException  extends RuntimeException {
	
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s  with %s :%s not found", resourceName, fieldName, fieldValue));
		
	}
	
}
