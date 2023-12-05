package com.movieticketing.MovieTicketing.model.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.movieticketing.MovieTicketing.model.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
	
	@NotEmpty
	private long contact;
	
	@NotEmpty
	private long quantity;
	
	@NotEmpty
	private String selectedSeats;
	
	@NotEmpty
	private long subTotal;
	
	@NotEmpty
	private long tax;
	
	@NotEmpty
	private long total;
	
	@NotEmpty
	private long movieId;
	
	@NotEmpty
	private boolean status;
	
	private LocalDate bookingDate;
	
	private LocalDateTime createdAt;
}
