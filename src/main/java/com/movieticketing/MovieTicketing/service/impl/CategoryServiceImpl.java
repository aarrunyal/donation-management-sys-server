package com.movieticketing.MovieTicketing.service.impl;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Category;
import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.service.CategoryService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		category.setCreatedAt(LocalDate.now());
		category = this.categoryRepository.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category oldCategory = this.categoryRepository.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category", "id", categoryId)));
		oldCategory.setId(categoryId);
		Category category = this.categoryRepository.save(oldCategory);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow((()->new ResourceNotFoundException("Category", "id", categoryId)));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category", "id", categoryId)));
		this.categoryRepository.delete(category);
		
	}

	

	
}
