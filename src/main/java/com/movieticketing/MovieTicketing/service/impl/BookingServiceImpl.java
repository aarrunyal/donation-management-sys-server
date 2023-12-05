package com.movieticketing.MovieTicketing.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Booking;
import com.movieticketing.MovieTicketing.model.Category;
import com.movieticketing.MovieTicketing.model.Movie;
import com.movieticketing.MovieTicketing.model.Theater;
import com.movieticketing.MovieTicketing.model.Dto.BookingDto;
import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.model.Dto.TheaterDto;
import com.movieticketing.MovieTicketing.repository.BookingRepository;
import com.movieticketing.MovieTicketing.repository.MovieRepository;
import com.movieticketing.MovieTicketing.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private BookingRepository bookingRepository;
	
	@Autowired 
	private MovieRepository movieRepository;
	
	@Override
	public BookingDto create(BookingDto bookingDto) {
		Movie movie = this.movieRepository.getById(bookingDto.getMovieId());
		Booking booking = this.modelMapper.map(bookingDto, Booking.class);
		booking.setCreatedAt(LocalDateTime.now());
		booking.setMovie(movie);
		booking = this.bookingRepository.save(booking);
		return this.modelMapper.map(booking, BookingDto.class);
	}

	@Override
	public BookingDto update(BookingDto bookingDto, Long bookingId) {
		Booking oldBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Booking", "id", bookingId)));
		
		if (bookingDto.getMovieId() != oldBooking.getMovie().getId()) {
			Movie movie = this.movieRepository.getOne(bookingDto.getMovieId());
			oldBooking.setMovie(movie);
		}
		oldBooking.setId(bookingId);
		oldBooking.setBookingDate(bookingDto.getBookingDate());
		oldBooking.setEmail(bookingDto.getEmail());
		oldBooking.setName(bookingDto.getName());
		oldBooking.setContact(bookingDto.getContact());
		oldBooking.setQuantity(bookingDto.getQuantity());
		oldBooking.setSelectedSeats(bookingDto.getSelectedSeats());
		oldBooking.setStatus(bookingDto.isStatus());
		oldBooking.setSubTotal(bookingDto.getSubTotal());
		oldBooking.setTax(bookingDto.getTax());
		oldBooking.setTotal(bookingDto.getTotal());
		Booking booking = this.bookingRepository.save(oldBooking);
		return this.modelMapper.map(booking, BookingDto.class);
	}

	@Override
	public BookingDto show(Long bookingId) {
		Booking booking = this.bookingRepository.findById(bookingId)
				.orElseThrow((()->new ResourceNotFoundException("Booking", "id", bookingId)));
		return this.modelMapper.map(booking, BookingDto.class);
	}

	@Override
	public List<BookingDto> all() {
		List<Booking> bookings = this.bookingRepository.findAll(Sort.by("id").descending());
		List<BookingDto> theaterDtos = bookings.stream().map((booking)->this.modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
		return theaterDtos;
	}

	@Override
	public void delete(Long bookingId) {
		Booking booking = this.bookingRepository.findById(bookingId)
				.orElseThrow((() -> new ResourceNotFoundException("Movie", "id", bookingId)));
		this.bookingRepository.delete(booking);
	}

}