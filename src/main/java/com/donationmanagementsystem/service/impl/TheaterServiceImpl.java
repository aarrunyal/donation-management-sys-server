package com.donationmanagementsystem.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.model.Movie;
import com.donationmanagementsystem.model.Theater;
import com.donationmanagementsystem.model.Dto.TheaterDto;
import com.donationmanagementsystem.repository.MovieRepository;
import com.donationmanagementsystem.repository.TheaterRepository;
import com.donationmanagementsystem.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	@Override
	public TheaterDto create(TheaterDto theaterDto) {
		Theater theater = this.modelMapper.map(theaterDto, Theater.class);
		theater.setCreatedAt(LocalDateTime.now());
		theater = this.theaterRepository.save(theater);
	
		return this.modelMapper.map(theater, TheaterDto.class);
	}

	@Override
	public TheaterDto update(TheaterDto theaterDto, Long theaterId) {
		Theater oldTheater =  this.theaterRepository.findById(theaterId).orElseThrow((()->new ResourceNotFoundException("Theater", "id", theaterId)));
//		Theater oldTheater = this.modelMapper.map(theaterDto, Theater.class);
		oldTheater.setNoOfRows(theaterDto.getNoOfRows());
		oldTheater.setSeatCapacity(theaterDto.getSeatCapacity());
		oldTheater.setSeatsInEachRow(theaterDto.getSeatsInEachRow());
		oldTheater.setStatus(theaterDto.isStatus());
		oldTheater.setTitle(theaterDto.getTitle());
		Theater theater = this.theaterRepository.save(oldTheater);
		return this.modelMapper.map(theater, TheaterDto.class);
	}

	@Override
	public TheaterDto show(Long theaterId) {
		Theater theater = this.theaterRepository.findById(theaterId)
				.orElseThrow((()->new ResourceNotFoundException("Category", "id", theaterId)));
		return this.modelMapper.map(theater, TheaterDto.class);
	}

	@Override
	public List<TheaterDto> all() {
		List<Theater> movies = this.theaterRepository.findAll(Sort.by("id").descending());
		List<TheaterDto> TheaterDtos = movies.stream().map((theater)->this.modelMapper.map(theater, TheaterDto.class)).collect(Collectors.toList());
		return TheaterDtos;
	}

	@Override
	public void delete(Long theaterId) {
		Theater theater = this.theaterRepository.findById(theaterId).orElseThrow((()->new ResourceNotFoundException("Theater", "id", theaterId)));
		this.theaterRepository.delete(theater);
		
	}
	
	public List<TheaterDto> getActiveList() {
		List<Theater> movies = this.theaterRepository.getByStatus(true);
		List<TheaterDto> theaterDtos = movies.stream().map((theater)->this.modelMapper.map(theater, TheaterDto.class)).collect(Collectors.toList());
		return theaterDtos ;
	}
}
