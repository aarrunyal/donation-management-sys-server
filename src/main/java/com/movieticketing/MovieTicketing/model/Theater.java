package com.movieticketing.MovieTicketing.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private boolean status;
	
	@Column(nullable=false)
	private int seatCapacity;
	
	@Column(nullable=false)
	private int noOfRows;
	
	@Column(nullable=false)
	private int seatInEachRow;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;

	
	
	public Theater() {
		super();
	}

	public Theater(long id, String title, boolean status, int seatCapacity, int noOfRows, int seatInEachRow,
			LocalDate createdAt) {
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

	@Override
	public String toString() {
		return "Theater [id=" + id + ", title=" + title + ", status=" + status + ", seatCapacity=" + seatCapacity
				+ ", noOfRows=" + noOfRows + ", seatInEachRow=" + seatInEachRow + ", createdAt=" + createdAt + "]";
	}

	
	
}
