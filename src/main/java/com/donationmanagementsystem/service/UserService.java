package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;

public interface UserService {

	
	ResponseEntity<ApiResponse> create(UserRequest userRequest);

	ResponseEntity<ApiResponse> update(UserRequest userRequest, Long userSettingId);

	ResponseEntity<UserResponse> show(Long userId);

	ResponseEntity<List<UserResponse>> all();

	ResponseEntity<ApiResponse> delete(Long userId);
	
	User getLoggedInUser();
}
