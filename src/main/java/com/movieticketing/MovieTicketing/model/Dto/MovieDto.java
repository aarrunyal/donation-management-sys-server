package com.movieticketing.MovieTicketing.model.Dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	
	private long id;
	@NotEmpty
	private String title;
	
	private String slug;
	
	@NotEmpty
	private String description;
	
	@NotNull
	private long price;
	
	@NotNull
	private int rating;
	
	@NotEmpty
	private String genre;
	
	@NotEmpty
	private String director;
	
	@NotEmpty
	private String starCasts;
	
	
	@NotNull
	private String playTime;
	
	
	@NotEmpty
	private String releaseYear;
	
	
	private boolean status;


}
