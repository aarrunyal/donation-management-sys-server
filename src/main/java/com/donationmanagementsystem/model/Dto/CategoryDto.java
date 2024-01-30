package com.donationmanagementsystem.model.Dto;

import java.util.Date;

import com.donationmanagementsystem.model.CategoryType;
import com.donationmanagementsystem.model.StatusType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;



public class CategoryDto {

	
	private long id;
	
	@NotEmpty(message="Title is required")
	private String title;
	
	@Column(nullable=false)
	private StatusType status;
	

	private CategoryType type;

	
	@Column(nullable=false, length=100)
	private Date createdAt;
	
	public CategoryDto() {
		super();
	}

	
	
	public CategoryDto(long id, @NotEmpty(message = "Title is required") String title, StatusType status,
			CategoryType type, Date createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.type = type;
		this.createdAt = createdAt;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}




	
	
}
