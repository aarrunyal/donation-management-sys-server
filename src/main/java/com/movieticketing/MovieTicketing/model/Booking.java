package com.movieticketing.MovieTicketing.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.movieticketing.MovieTicketing.model.Dto.MovieDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private long contact;
	
	@Column(nullable=false)
	private long quantity;
	
	@Column(nullable=false)
	private String selectedSeats;
	
	@Column(nullable=false)
	private long subTotal;
	
	@Column(nullable=false)
	private long tax;
	
	@Column(nullable=false)
	private long total;
	
	@ManyToOne
	private Movie movie;
	
	private boolean status;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
}
