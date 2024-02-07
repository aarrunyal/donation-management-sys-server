package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;

public interface UserService {

	
	ResponseEntity<ApiResponse> create(UserSettingRequest userSettingRequest, User user);

	ResponseEntity<ApiResponse> update(UserSettingRequest userSettingRequest, Long userSettingId, User user);

	ResponseEntity<UserResponse> show(Long userSettingId);

	ResponseEntity<List<UserResponse>> all();

	ApiResponse delete(Long userSettingId);
	
	User getLoggedInUser();
}
