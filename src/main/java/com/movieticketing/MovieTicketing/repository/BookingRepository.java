package com.movieticketing.MovieTicketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketing.MovieTicketing.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
