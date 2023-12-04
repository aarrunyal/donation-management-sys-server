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
	private String startCasts;
	
	
	@NotNull
	private LocalTime playTime;
	
	@NotNull
	private LocalDate createdAt;
	
	
	@NotEmpty
	private String releaseYear;
	
	@NotNull
	@AssertTrue
	private boolean status;

	
	public MovieDto() {
		super();
	}


	public MovieDto(long id, @NotEmpty String title, @NotEmpty String slug, @NotEmpty String description,
			@NotNull long price, @NotNull int rating, @NotEmpty String genre, @NotEmpty String director,
			@NotEmpty String startCasts, @NotNull LocalTime playTime, @NotNull LocalDate createdAt,
			@NotEmpty String releaseYear, @NotNull @AssertTrue boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.genre = genre;
		this.director = director;
		this.startCasts = startCasts;
		this.playTime = playTime;
		this.createdAt = createdAt;
		this.releaseYear = releaseYear;
		this.status = status;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getStartCasts() {
		return startCasts;
	}


	public void setStartCasts(String startCasts) {
		this.startCasts = startCasts;
	}


	public LocalTime getPlayTime() {
		return playTime;
	}


	public void setPlayTime(LocalTime playTime) {
		this.playTime = playTime;
	}


	public LocalDate getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}


	public String getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getSlug() {
		return slug;
	}







	public void setSlug(String slug) {
		this.slug = slug;
	}
}
