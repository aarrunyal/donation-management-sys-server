package com.movieticketing.MovieTicketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.service.impl.CategoryServiceImpl;

public class CategoryService implements CategoryServiceImpl {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		// TODO Auto-generated method stub
		
	}

}
