package com.donationmanagementsystem.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserSetting;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserSettingResponse;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.repository.UserSettingRepository;
import com.donationmanagementsystem.service.UserSettingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSettingServiceImpl implements UserSettingService{
	
	private final UserSettingRepository userSettingRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> create(UserSettingRequest userSettingRequest, User user) {
		UserSetting savedSetting = userSettingRepository.findByUserId(user.getId())			
				.orElseThrow(()->	new ResourceNotFoundException("User", "user id", user.getId()));
		
		UserSetting setting = this.modelMapper.map(userSettingRequest,UserSetting.class);
		setting.setUser(user);
		userSettingRepository.save(setting);
		return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Setting has been creeated successfully"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse> update(UserSettingRequest userSettingRequest, Long userSettingId, User user) {
		UserSetting oldSetting = userSettingRepository.findByUserIdAndUserSettingId(user.getId(), userSettingId)			
				.orElseThrow(()->	new ResourceNotFoundException("User", "user id", user.getId()));
		
		oldSetting
		.builder()
		.contactNo(userSettingRequest.getContactNo())
		.alternativeContactNo(userSettingRequest.getAlternativeContactNo())
		.build();
		userSettingRepository.save(oldSetting);
		return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Setting has been updated successfully"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserSettingResponse> show(Long userSettingId) {
		UserSetting savedSetting = userSettingRepository.findById(userSettingId)			
				.orElseThrow(()->	new ResourceNotFoundException("User", "user id", userSettingId));
		UserSettingResponse response = this.modelMapper.map(savedSetting, UserSettingResponse.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserSettingResponse>> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse delete(Long userSettingId) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
