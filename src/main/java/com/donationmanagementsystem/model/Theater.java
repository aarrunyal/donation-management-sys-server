package com.donationmanagementsystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private int seatsInEachRow;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdAt;


	
	
}
