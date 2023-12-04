package com.movieticketing.MovieTicketing.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.movieticketing.MovieTicketing.model.Dto.MovieDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}

