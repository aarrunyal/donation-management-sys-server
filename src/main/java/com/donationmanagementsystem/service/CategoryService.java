package com.donationmanagementsystem.service;

import java.util.List;

import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserSettingResponse;

public interface CategoryService {
	UserSettingResponse create(UserSettingRequest theaterDto);

	UserSettingResponse update(UserSettingRequest theaterDto, Long theaterId);

	UserSettingResponse show(Long theaterDto);

	List<UserSettingResponse> all();

	ApiResponse delete(Long theaterDto);
}
