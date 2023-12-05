package com.movieticketing.MovieTicketing.service;

import java.util.List;

import com.movieticketing.MovieTicketing.model.Dto.BookingDto;
import com.movieticketing.MovieTicketing.model.Dto.MovieDto;

public interface BookingService {
	
	BookingDto create(BookingDto bookingDto);

	BookingDto update(BookingDto bookingDto, Long bookingId);

	BookingDto show(Long bookingId);

	List<BookingDto> all();

	void delete(Long bookingId);

}
