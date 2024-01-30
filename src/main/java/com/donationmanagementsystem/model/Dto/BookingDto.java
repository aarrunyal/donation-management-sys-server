package com.donationmanagementsystem.model.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.donationmanagementsystem.model.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotNull
	private long contact;
	
	@NotNull
	private long quantity;
	
	@NotEmpty
	private String selectedSeats;
	
	@NotNull
	private long subTotal;
	
	@NotNull
	private long tax;
	
	@NotNull
	private long total;
	
	@NotNull
	private long movieId;
	
	@NotNull
	private boolean status;
	
	private String bookingDate;
	
	private LocalDateTime createdAt;
	
	private Movie movie;
}
