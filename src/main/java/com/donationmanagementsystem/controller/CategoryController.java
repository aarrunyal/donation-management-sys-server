package com.donationmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.model.Dto.CategoryDto;
import com.donationmanagementsystem.model.response.ApiResponse;
import com.donationmanagementsystem.service.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value ="/api/category")
@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId){
		CategoryDto categoryDto2 = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.OK);
	}
//	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getByCategoryId(@PathVariable Long categoryId){
		CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
//	
	@GetMapping(value="/list")
	public ResponseEntity<List<CategoryDto>> getByCategories(){
		List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
	}
//	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category has been deleted !!", true), HttpStatus.OK);
	}
	
}