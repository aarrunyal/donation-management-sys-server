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
import com.movieticketing.MovieTicketing.model.Dto.TheaterDto;
import com.movieticketing.MovieTicketing.model.response.ApiResponse;
import com.movieticketing.MovieTicketing.service.MovieService;
import com.movieticketing.MovieTicketing.service.TheaterService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("api/theater")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	@PostMapping("")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<TheaterDto> createTheater(@Valid @RequestBody TheaterDto theaterDto){
		TheaterDto theater = this.theaterService.create(theaterDto);
		return new ResponseEntity<TheaterDto>(theater, HttpStatus.CREATED);
	}
	
	@PutMapping("/{theaterId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public ResponseEntity<TheaterDto> updateTheater(@Valid @RequestBody TheaterDto theaterDto, @PathVariable Long theaterId){
		TheaterDto updatedDto = this.theaterService.update(theaterDto, theaterId);
		return new ResponseEntity<TheaterDto>(updatedDto, HttpStatus.OK);
	}
//	
	@GetMapping("/{theaterId}")
	public ResponseEntity<TheaterDto> getByTheaterId(@PathVariable Long theaterId){
		TheaterDto theaterDto = this.theaterService.show(theaterId);
		return new ResponseEntity<TheaterDto>(theaterDto, HttpStatus.OK);
	}
//	
	@GetMapping("")
	public ResponseEntity<List<TheaterDto>> getAll(){
		List<TheaterDto> theaterDtos = this.theaterService.all();
		return new ResponseEntity<List<TheaterDto>>(theaterDtos, HttpStatus.OK);
	}
//	
	@DeleteMapping("/{theaterId}")
	public ResponseEntity<ApiResponse> deleteTheater(@PathVariable Long theaterId){
		this.theaterService.delete(theaterId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("movie has been deleted !!", true), HttpStatus.OK);
	}
}
