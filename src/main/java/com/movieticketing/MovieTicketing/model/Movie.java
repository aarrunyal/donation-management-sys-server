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
	private String startCasts;
	
	@Column(nullable=false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime playTime;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	
	
	@Column(nullable=false)
	private String releaseYear;
	
	@Column(nullable=false)
	private boolean status;

	
	
	public Movie() {
		super();
	}



	



	public Movie(long id, String title, String slug, String description, long price, int rating, String genre,
			String director, String startCasts, LocalTime playTime, LocalDate createdAt, String releaseYear,
			boolean status) {
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







	public String getSlug() {
		return slug;
	}







	public void setSlug(String slug) {
		this.slug = slug;
	}

	
	
	
	
}
