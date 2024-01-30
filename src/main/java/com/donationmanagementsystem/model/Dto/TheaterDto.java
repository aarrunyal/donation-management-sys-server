package com.donationmanagementsystem.model.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.donationmanagementsystem.model.Theater;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private int seatsInEachRow;

	
	
	private LocalDateTime createdAt;
	
	private Long theaterId;
	

}
