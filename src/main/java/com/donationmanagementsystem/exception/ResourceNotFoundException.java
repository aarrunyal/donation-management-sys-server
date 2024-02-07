package com.donationmanagementsystem.exception;



public class ResourceNotFoundException  extends RuntimeException {
	
	public String resourceName;
	public String fieldName;
	public Object fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s  with %s :%s not found", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName =  fieldName;
		this.fieldValue = fieldValue;
		
	}
	
}
