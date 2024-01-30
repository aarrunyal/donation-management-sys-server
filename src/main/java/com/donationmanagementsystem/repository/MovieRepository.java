package com.donationmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.model.Movie;
import com.donationmanagementsystem.model.Dto.MovieDto;

public interface MovieRepository  extends JpaRepository<Movie, Long>{

	@Query(value = "SELECT m FROM Movie m WHERE m.slug LIKE %:key% OR m.title LIKE :key%")
	List<Movie> searchByMovieKey(@Param("key") String key);
	
	@Query(value = "SELECT * FROM movie m WHERE status = true order by id DESC", nativeQuery = true)
	List<Movie> getActiveMovies();
	
	
}
