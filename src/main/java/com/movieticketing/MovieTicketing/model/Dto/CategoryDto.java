package com.movieticketing.MovieTicketing.model.Dto;

import com.movieticketing.MovieTicketing.model.CategoryType;

import jakarta.validation.constraints.NotEmpty;



public class CategoryDto {

	
	@NotEmpty(message="Title is required")
	private String title;
	
	@NotEmpty(message="State of category needs to be either active or not")
	private boolean isActive;
	
	@NotEmpty(message="Type is required")
	private CategoryType type;
}
