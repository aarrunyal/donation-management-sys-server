package com.movieticketing.MovieTicketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketing.MovieTicketing.model.Movie;

public interface MovieRepository  extends JpaRepository<Movie, Long>{

}
