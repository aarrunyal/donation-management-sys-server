package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserSettingResponse;

public interface UserSettingService {

	ResponseEntity<ApiResponse> create(UserSettingRequest userSettingRequest, User user);

	ResponseEntity<ApiResponse> update(UserSettingRequest userSettingRequest, Long userSettingId, User user);

	ResponseEntity<UserSettingResponse> show(User user);

	ResponseEntity<List<UserSettingResponse>> all();

	ResponseEntity<ApiResponse> delete(Long userSettingId, User user);
}
