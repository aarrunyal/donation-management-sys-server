package com.movieticketing.MovieTicketing.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.Movie;
import com.movieticketing.MovieTicketing.model.Theater;
import com.movieticketing.MovieTicketing.model.Dto.TheaterDto;
import com.movieticketing.MovieTicketing.repository.MovieRepository;
import com.movieticketing.MovieTicketing.repository.TheaterRepository;
import com.movieticketing.MovieTicketing.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	@Override
	public TheaterDto create(TheaterDto theaterDto) {
		Theater theater = this.modelMapper.map(theaterDto, Theater.class);
		theater.setCreatedAt(LocalDate.now());
		theater = this.theaterRepository.save(theater);
		
		return this.modelMapper.map(theater, TheaterDto.class);
	}

	@Override
	public TheaterDto update(TheaterDto theaterDto, Long theaterId) {
		Theater theater =  this.theaterRepository.findById(theaterId).orElseThrow((()->new ResourceNotFoundException("Theater", "id", theaterId)));
		Theater oldTheater = this.modelMapper.map(theaterDto, Theater.class);
		oldTheater.setId(theaterId);
		oldTheater.setCreatedAt(theater.getCreatedAt());
		oldTheater = this.theaterRepository.save(oldTheater);
		return this.modelMapper.map(oldTheater, TheaterDto.class);
	}

	@Override
	public TheaterDto show(Long theaterId) {
		Theater theater = this.theaterRepository.findById(theaterId)
				.orElseThrow((()->new ResourceNotFoundException("Category", "id", theaterId)));
		return this.modelMapper.map(theater, TheaterDto.class);
	}

	@Override
	public List<TheaterDto> all() {
		List<Theater> movies = this.theaterRepository.findAll();
		List<TheaterDto> TheaterDtos = movies.stream().map((theater)->this.modelMapper.map(theater, TheaterDto.class)).collect(Collectors.toList());
		return TheaterDtos;
	}

	@Override
	public void delete(Long theaterId) {
		Theater theater = this.theaterRepository.findById(theaterId).orElseThrow((()->new ResourceNotFoundException("Theater", "id", theaterId)));
		this.theaterRepository.delete(theater);
		
	}
}
