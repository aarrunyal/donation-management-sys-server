package com.donationmanagementsystem.service;

import java.util.List;

import com.donationmanagementsystem.payload.request.UserAddressRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserAddressResponse;

public interface UserAddressService {
	
	UserAddressResponse create(UserAddressRequest theaterDto);

	UserAddressResponse update(UserAddressRequest theaterDto, Long theaterId);

	UserAddressResponse show(Long theaterDto);

	List<UserAddressResponse> all();

	ApiResponse delete(Long theaterDto);
}
