package com.movieticketing.MovieTicketing.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(nullable=false, length=100)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;

	public Category() {
		super();
	}

	public Category(long id, String title, StatusType status, CategoryType type, LocalDate createdAt) {
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	

}

