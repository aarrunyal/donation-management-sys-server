package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.UserRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.service.UserService;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<UserResponse> show(Long userSettingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<UserResponse>> all() {
		List<User> users = userRepository.findAll(Helper.sortByAsc("id", "DESC"));
		List<UserResponse> userResponses = users.stream().map((user) -> this.modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse> delete(Long userId) {
		try {
			User savedUser = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
			if (savedUser != null) {
				userRepository.delete(savedUser);
				return ResponseMessage.ok("User deleted successfully");
			}
			return ResponseMessage.internalServerError("");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError("");
		}
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName() != null) {
			return userRepository.findByEmail(authentication.getName()).orElse(null);
		}
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> create(UserRequest userRequest) {
		try {
			Optional<User> savedUser = userRepository.findByEmail(userRequest.getEmail());
			if (savedUser.isPresent())
				return ResponseMessage.notAcceptable(null);

			User newUser = this.modelMapper.map(userRequest, User.class);
			newUser.setPassword(encoder.encode(userRequest.getPassword()));
			userRepository.save(newUser);
			return ResponseMessage.created("User has been creeated successfully");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError(null);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> update(UserRequest userRequest, Long userId) {
		try {
			User savedUser = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User ", " id", userId));

			savedUser.setFirstName(userRequest.getFirstName());
			savedUser.setLastName(userRequest.getLastName());
			savedUser.setRole(userRequest.getRole());
			userRepository.save(savedUser);
			return ResponseMessage.ok("User has been updated successfully");
		} catch (Exception ex) {
			return ResponseMessage.internalServerError(null);
		}
	}

}
