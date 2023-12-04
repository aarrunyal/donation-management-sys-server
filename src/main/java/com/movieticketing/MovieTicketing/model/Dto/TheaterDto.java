package com.movieticketing.MovieTicketing.model.Dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class TheaterDto {

	
	private long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private boolean status;
	
	@NotNull
	private int seatCapacity;
	
	@NotNull
	private int noOfRows;
	
	@NotNull
	private int seatInEachRow;

	private LocalDate createdAt;

	public TheaterDto() {
		super();
	}
	
	

	public TheaterDto(long id, @NotNull String title, @NotNull boolean status, @NotNull int seatCapacity,
			@NotNull int noOfRows, @NotNull int seatInEachRow, LocalDate createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.seatCapacity = seatCapacity;
		this.noOfRows = noOfRows;
		this.seatInEachRow = seatInEachRow;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public int getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}

	public int getSeatInEachRow() {
		return seatInEachRow;
	}

	public void setSeatInEachRow(int seatInEachRow) {
		this.seatInEachRow = seatInEachRow;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	

	
	
	
}
