package com.donationmanagementsystem.service;

import java.util.List;

import com.donationmanagementsystem.model.Dto.TheaterDto;



public interface TheaterService {

	TheaterDto create(TheaterDto theaterDto);

	TheaterDto update(TheaterDto theaterDto, Long theaterId);

	TheaterDto show(Long theaterDto);

	List<TheaterDto> all();

	void delete(Long theaterDto);
	
	List<TheaterDto> getActiveList();
}
