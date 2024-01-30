package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.donationmanagementsystem.model.Dto.CategoryDto;
import com.donationmanagementsystem.repository.CategoryRepository;
import com.donationmanagementsystem.service.impl.CategoryServiceImpl;

public interface CategoryService  {

	
CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Long categoryId);
	

}
