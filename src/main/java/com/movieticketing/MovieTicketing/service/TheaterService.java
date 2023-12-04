package com.movieticketing.MovieTicketing.service;

import java.util.List;

import com.movieticketing.MovieTicketing.model.Dto.MovieDto;
import com.movieticketing.MovieTicketing.model.Dto.TheaterDto;

public interface TheaterService {

	TheaterDto create(TheaterDto theaterDto);

	TheaterDto update(TheaterDto theaterDto, Long theaterId);

	TheaterDto show(Long theaterDto);

	List<TheaterDto> all();

	void delete(Long theaterDto);
}
