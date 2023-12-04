package com.movieticketing.MovieTicketing.service;

import java.util.List;

import com.movieticketing.MovieTicketing.model.Dto.MovieDto;


public interface MovieService {
	MovieDto create(MovieDto movieDto);

	MovieDto update(MovieDto movieDto, Long movieId);

	MovieDto show(Long movieId);

	List<MovieDto> all();

	void delete(Long movieId);

	List<MovieDto> searchByKey(String key);
}
