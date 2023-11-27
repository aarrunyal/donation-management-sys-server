package com.movieticketing.MovieTicketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.service.impl.CategoryServiceImpl;

public interface CategoryService  {

	
CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Long categoryId);
	

}
