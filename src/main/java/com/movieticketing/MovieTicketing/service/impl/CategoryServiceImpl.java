package com.movieticketing.MovieTicketing.service.impl;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Category;
import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{


	private ModelMapper modelMapper;
	private CategoryRepository categoryRepository;
	
	CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository){
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		category = this.categoryRepository.save(category);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category", "id", categoryId)));
		category.setTitle(categoryDto.getTitle());
		category = this.categoryRepository.save(category);
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
