package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserAddressRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserAddressResponse;

public interface UserAddressService {

	ResponseEntity<ApiResponse> create(UserAddressRequest userAddressRequest, User user);

	ResponseEntity<ApiResponse> update(UserAddressRequest userAddressRequest, Long userAddressId, User user);

	ResponseEntity<UserAddressResponse> show(Long userAddressId, User user);

	ResponseEntity<List<UserAddressResponse>> all(User user);

	ResponseEntity<ApiResponse> delete(Long userAddressId, User user);

    ResponseEntity<UserAddressResponse> byUser(Long id);
}
