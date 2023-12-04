package com.movieticketing.MovieTicketing.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String slug;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private long price;
	
	@Column(nullable=false)
	private int rating;
	
	@Column(nullable=false)
	private String genre;
	
	@Column(nullable=false)
	private String director;
	
	@Column(nullable=false)
	private String starCasts;
	
	@Column(nullable=false)
//	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private String playTime;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	
	
	@Column(nullable=false)
	private String releaseYear;
	
	@Column(nullable=false)
	private boolean status;
	
	
	
}
