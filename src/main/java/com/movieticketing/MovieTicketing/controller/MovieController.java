package com.movieticketing.MovieTicketing.controller;

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

import com.movieticketing.MovieTicketing.model.Dto.MovieDto;
import com.movieticketing.MovieTicketing.model.response.ApiResponse;
import com.movieticketing.MovieTicketing.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping("")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto){
		MovieDto movie = this.movieService.create(movieDto);
		return new ResponseEntity<MovieDto>(movie, HttpStatus.CREATED);
	}
	
	@PutMapping("/{movieId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movieDto, @PathVariable Long movieId){
		MovieDto updateDto = this.movieService.update(movieDto, movieId);
		return new ResponseEntity<MovieDto>(updateDto, HttpStatus.OK);
	}
//	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getByMovieId(@PathVariable Long movieId){
		MovieDto movieDto = this.movieService.show(movieId);
		return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);
	}
//	
	@GetMapping(value="")
	public ResponseEntity<List<MovieDto>> getAll(){
		List<MovieDto> movieDtos = this.movieService.all();
		return new ResponseEntity<List<MovieDto>>(movieDtos, HttpStatus.OK);
	}
//	
	@DeleteMapping("/{movieId}")
	public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long movieId){
		this.movieService.delete(movieId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("movie has been deleted !!", true), HttpStatus.OK);
	}
}
