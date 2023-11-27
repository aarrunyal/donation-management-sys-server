package com.movieticketing.MovieTicketing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private StatusType status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable=false, length=100)
	private CategoryType type;

	public Category() {
		super();
	}



	public Category(long id, String title, StatusType status, CategoryType type) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.type = type;
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
	
	
	
	
}

