package com.donationmanagementsystem.service.impl;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public ResponseEntity<ApiResponse> create(UserSettingRequest userSettingRequest, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> update(UserSettingRequest userSettingRequest, Long userSettingId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponse> show(Long userSettingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<UserResponse>> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse delete(Long userSettingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != null) {
			System.out.println("heloo");
			return userRepository.findByEmail(authentication.getName()).orElse(null);
		}
		return null;
	}

	

}
