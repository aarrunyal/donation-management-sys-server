package com.donationmanagementsystem.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.model.Booking;
import com.donationmanagementsystem.model.Category;
import com.donationmanagementsystem.model.Movie;
import com.donationmanagementsystem.model.Theater;
import com.donationmanagementsystem.model.Dto.CategoryDto;
import com.donationmanagementsystem.model.Dto.MovieDto;
import com.donationmanagementsystem.repository.BookingRepository;
import com.donationmanagementsystem.repository.CategoryRepository;
import com.donationmanagementsystem.repository.MovieRepository;
import com.donationmanagementsystem.repository.TheaterRepository;
import com.donationmanagementsystem.service.MovieService;
import com.donationmanagementsystem.utils.Slugify;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

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
		List<Booking> bookings = this.bookingRepository.getBookingByMovieId(movieId);
//		if(bookings.size()>0) {
//			ListIterator<Booking>
//            iterator = bookings.listIterator();
//			 while (iterator.hasNext()) {
//				 	iterator.next().setMovie(null);
//	               this.bookingRepository.delete(iterator.next());
//	            }
//			
//		}
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
