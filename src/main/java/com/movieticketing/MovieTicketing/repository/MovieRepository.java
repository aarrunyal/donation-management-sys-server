package com.movieticketing.MovieTicketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movieticketing.MovieTicketing.model.Movie;
import com.movieticketing.MovieTicketing.model.Dto.MovieDto;

public interface MovieRepository  extends JpaRepository<Movie, Long>{

	@Query(value = "SELECT m FROM Movie m WHERE m.slug LIKE %:key% OR m.title LIKE :key%")
	List<Movie> searchByMovieKey(@Param("key") String key);
	
}
