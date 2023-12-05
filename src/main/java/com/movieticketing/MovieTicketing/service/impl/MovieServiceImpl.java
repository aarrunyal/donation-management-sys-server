package com.movieticketing.MovieTicketing.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Category;
import com.movieticketing.MovieTicketing.model.Movie;
import com.movieticketing.MovieTicketing.model.Theater;
import com.movieticketing.MovieTicketing.model.Dto.CategoryDto;
import com.movieticketing.MovieTicketing.model.Dto.MovieDto;
import com.movieticketing.MovieTicketing.repository.CategoryRepository;
import com.movieticketing.MovieTicketing.repository.MovieRepository;
import com.movieticketing.MovieTicketing.repository.TheaterRepository;
import com.movieticketing.MovieTicketing.service.MovieService;
import com.movieticketing.MovieTicketing.utils.Slugify;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Override
	public boolean create(MovieDto movieDto) {
		Theater theater = this.theaterRepository.getOne(movieDto.getTheatreId());
		Movie movie = this.modelMapper.map(movieDto, Movie.class);
		movie.setCreatedAt(LocalDateTime.now());
		movie.setSlug(Slugify.makeSlug(movieDto.getTitle()));
		movie.setStatus(true);
		movie.setTheatre(theater);
		movie = this.movieRepository.save(movie);
		return true;
	}

	@Override
	public MovieDto update(MovieDto movieDto, Long movieId) {
		Movie oldMovie = this.movieRepository.findById(movieId)
				.orElseThrow((() -> new ResourceNotFoundException("Movie", "id", movieId)));
		if (movieDto.getTheatreId() != oldMovie.getTheatre().getId()) {
			Theater theater = this.theaterRepository.getOne(movieDto.getTheatreId());
			oldMovie.setTheatre(theater);
		}
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
		oldMovie.setCurrency(movieDto.getCurrency());
		Movie movie = this.movieRepository.save(oldMovie);
		return this.modelMapper.map(movie, MovieDto.class);
	}

	@Override
	public MovieDto show(Long movieId) {
		Movie movie = this.movieRepository.findById(movieId)
				.orElseThrow((() -> new ResourceNotFoundException("Movie", "id", movieId)));
		MovieDto movieDto = this.modelMapper.map(movie, MovieDto.class);
		if (movie.getTheatre() != null) {
//			Theater theater = this.theaterRepository.getOne(movie.getTheatre().getId());
			movieDto.setTheatre(movie.getTheatre());
		}
		return movieDto; 
	}

	@Override
	public List<MovieDto> all(boolean active) {
		List<Movie> movies = new ArrayList<>();
		if(active==false)
			movies = this.movieRepository.findAll(Sort.by("id").descending());
		else
			movies = this.movieRepository.getActiveMovies();
		List<MovieDto> movieDtos = movies.stream().map((movie) -> this.modelMapper.map(movie, MovieDto.class))
				.collect(Collectors.toList());
		return movieDtos;
	}

	@Override
	public void delete(Long movieId) {
		Movie movie = this.movieRepository.findById(movieId)
				.orElseThrow((() -> new ResourceNotFoundException("Movie", "id", movieId)));
		this.movieRepository.delete(movie);
	}

	@Override
	public List<MovieDto> searchByKey(String key) {
		List<Movie> movies = this.movieRepository.searchByMovieKey(key);
		List<MovieDto> movieDtos = movies.stream().map((movie) -> this.modelMapper.map(movie, MovieDto.class))
				.collect(Collectors.toList());
		return movieDtos;
	}

}
