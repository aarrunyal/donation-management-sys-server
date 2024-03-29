package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserSetting;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserSettingResponse;
import com.donationmanagementsystem.repository.UserSettingRepository;
import com.donationmanagementsystem.service.UserSettingService;
import com.donationmanagementsystem.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSettingServiceImpl implements UserSettingService {

	private final UserSettingRepository userSettingRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> create(UserSettingRequest userSettingRequest, User user) {
		try {
			Optional<UserSetting> savedSetting = userSettingRepository.findByUserId(user.getId());
			if (!savedSetting.isEmpty())
				return ResponseMessage.notAcceptable(null);

			UserSetting setting = this.modelMapper.map(userSettingRequest, UserSetting.class);
			setting.setUser(user);
			userSettingRepository.save(setting);
			return ResponseMessage.created("Setting has been creeated successfully");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError(null);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> update(UserSettingRequest userSettingRequest, Long userSettingId, User user) {
		try {
			UserSetting savedSetting = userSettingRepository.findByUserId(user.getId())
					.orElseThrow(() -> new ResourceNotFoundException("User Setting", "user id", user.getId()));

			savedSetting.setContactNo(userSettingRequest.getContactNo());
			savedSetting.setAlternativeContactNo(userSettingRequest.getAlternativeContactNo());
			userSettingRepository.save(savedSetting);
			return ResponseMessage.ok("Setting has been updated successfully");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError(null);
		}

	}

	@Override
	public ResponseEntity<UserSettingResponse> show(User user) {
		UserSetting savedSetting = userSettingRepository.findByUserId(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User Setting", "user id", user.getId()));
		return new ResponseEntity<>(this.modelMapper.map(savedSetting, UserSettingResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserSettingResponse>> all() {

		List<UserSetting> usersettings = userSettingRepository.findAll();
		List<UserSettingResponse> userSettingResponses = usersettings
				.stream()
				.map(
						(setting) -> this.modelMapper
								.map(setting, UserSettingResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserSettingResponse>>(userSettingResponses, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ApiResponse> delete(Long userSettingId, User user) {
		try {
			UserSetting savedSetting = userSettingRepository.findByUserIdAndId(user.getId(), userSettingId)
					.orElseThrow(() -> new ResourceNotFoundException("User Setting", "user id", user.getId()));
			if (savedSetting != null) {
				userSettingRepository.delete(savedSetting);
			}
			return ResponseMessage.ok("User setting deleted successfully");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError("");
		}
	}

}
