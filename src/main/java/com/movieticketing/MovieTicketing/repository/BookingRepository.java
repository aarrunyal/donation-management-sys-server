package com.movieticketing.MovieTicketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movieticketing.MovieTicketing.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	
	@Query(value="SELECT b.selected_seats FROM booking b where b.status= true and b.selected_seats IS NOT NULL AND b.booking_date like :booking_date% AND b.movie_id = :movie_id", nativeQuery = true)
	List<String> getBookedSeatByBookingDateAndMovieId(@Param("booking_date") String bookingDate, @Param("movie_id") String movieId);
}
