package com.movieticketing.MovieTicketing.service.impl;

import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import java.util.List;

public interface CategoryServiceImpl {

	

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Long categoryId);
}
