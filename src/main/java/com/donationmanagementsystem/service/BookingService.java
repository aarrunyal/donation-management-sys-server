package com.donationmanagementsystem.service;

import java.util.HashMap;
import java.util.List;

import com.donationmanagementsystem.model.Dto.BookingDto;
import com.donationmanagementsystem.model.Dto.MovieDto;

public interface BookingService {
	
	boolean create(BookingDto bookingDto);

	BookingDto update(BookingDto bookingDto, Long bookingId);

	BookingDto show(Long bookingId);

	List<BookingDto> all();

	void delete(Long bookingId);
	
	List<Integer> getBookedSeat(HashMap<String, String> object);
	
	boolean cancelBooking(long bookingId);

}
