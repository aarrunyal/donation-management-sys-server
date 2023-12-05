package com.movieticketing.MovieTicketing.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieticketing.MovieTicketing.model.Dto.BookingDto;
import com.movieticketing.MovieTicketing.model.Dto.MovieDto;
import com.movieticketing.MovieTicketing.model.Dto.TheaterDto;
import com.movieticketing.MovieTicketing.model.response.ApiResponse;
import com.movieticketing.MovieTicketing.service.BookingService;
import com.movieticketing.MovieTicketing.service.MovieService;
import com.movieticketing.MovieTicketing.service.TheaterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<ApiResponse> createBooking(@Valid @RequestBody BookingDto bookingDto){
		boolean flag = this.bookingService.create(bookingDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Bookin has been created !!", true), HttpStatus.OK);
	}
	
	@PutMapping("/{bookingId}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<BookingDto> updateTheater(@Valid @RequestBody BookingDto bookingDto, @PathVariable Long bookingId){
		BookingDto updatedDto = this.bookingService.update(bookingDto, bookingId);
		return new ResponseEntity<BookingDto>(updatedDto, HttpStatus.OK);
	}
//	
	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDto> getByTheaterId(@PathVariable Long bookingId){
		BookingDto bookingDto = this.bookingService.show(bookingId);
		return new ResponseEntity<BookingDto>(bookingDto, HttpStatus.OK);
	}
//	
	@GetMapping("")
	public ResponseEntity<List<BookingDto>> getAll(){
		List<BookingDto> bookingDtos = this.bookingService.all();
		return new ResponseEntity<List<BookingDto>>(bookingDtos, HttpStatus.OK);
	}
//	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<ApiResponse> deleteTheater(@PathVariable Long bookingId){
		this.bookingService.delete(bookingId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Booking has been deleted !!", true), HttpStatus.OK);
	}
	
	@PostMapping("/booked_seats")
	public ResponseEntity<List<Integer>> getBookedSeat(@RequestBody HashMap<String, String> data){
		List<Integer> seats = 	this.bookingService.getBookedSeat(data);
		return new ResponseEntity<List<Integer>>(seats, HttpStatus.OK);
	}
	
	@GetMapping("/inactive/{bookingId}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<ApiResponse> cancelBooking(@PathVariable long bookingId){
		boolean flag = this.bookingService.cancelBooking(bookingId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Booking has been cancelled !!", true), HttpStatus.OK);
	}
}
