package com.movieticketing.MovieTicketing.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Category;
import com.movieticketing.MovieTicketing.model.Movie;
import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.model.Dto.MovieDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.repository.MovieRepository;
import com.movieticketing.MovieTicketing.service.MovieService;
import com.movieticketing.MovieTicketing.utils.Slugify;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@Override
	public MovieDto create(MovieDto movieDto) {
		Movie movie = this.modelMapper.map(movieDto, Movie.class);
		movie.setCreatedAt(LocalDate.now());
		movie.setSlug(Slugify.makeSlug(movieDto.getTitle()));
		movie.setStatus(true);
		movie = this.movieRepository.save(movie);
		return this.modelMapper.map(movie, MovieDto.class);
	}

	@Override
	public MovieDto update(MovieDto movieDto, Long movieId) {
		Movie oldMovie = this.movieRepository.findById(movieId).orElseThrow((()->new ResourceNotFoundException("Movie", "id", movieId)));
		oldMovie.setId(movieId);
		oldMovie.setDescription(movieDto.getDescription());
		oldMovie.setDirector(movieDto.getDirector());
		oldMovie.setGenre(movieDto.getGenre());
		oldMovie.setPlayTime(movieDto.getPlayTime());
		oldMovie.setPrice(movieDto.getPrice());
		oldMovie.setRating(movieDto.getRating());
		oldMovie.setReleaseYear(movieDto.getReleaseYear());
		oldMovie.setStarCasts(movieDto.getStarCasts());
		oldMovie.setStatus(movieDto.isStatus());
		oldMovie.setTitle(movieDto.getTitle());
		Movie movie = this.movieRepository.save(oldMovie);
		return this.modelMapper.map(movie, MovieDto.class);
	}

	@Override
	public MovieDto show(Long movieId) {
		Movie movie = this.movieRepository.findById(movieId)
				.orElseThrow((()->new ResourceNotFoundException("Movie", "id", movieId)));
		return this.modelMapper.map(movie, MovieDto.class);
	}

	@Override
	public List<MovieDto> all() {
		List<Movie> movies = this.movieRepository.findAll();
		List<MovieDto> movieDtos = movies.stream().map((movie)->this.modelMapper.map(movie, MovieDto.class)).collect(Collectors.toList());
		return movieDtos;
	}

	@Override
	public void delete(Long movieId) {
		Movie movie = this.movieRepository.findById(movieId).orElseThrow((()->new ResourceNotFoundException("Movie", "id", movieId)));
		this.movieRepository.delete(movie);
		
	}
}
