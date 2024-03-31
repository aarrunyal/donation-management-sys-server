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
import com.donationmanagementsystem.entity.UserAddress;
import com.donationmanagementsystem.entity.UserSetting;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.UserDetailRequest;
import com.donationmanagementsystem.payload.request.UserRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.repository.UserAddressRepository;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.repository.UserSettingRepository;
import com.donationmanagementsystem.service.UserService;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;
import com.stripe.model.PaymentLink.CustomField.Dropdown.Option;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final UserAddressRepository userAddressRepository;

	private final UserSettingRepository userSettingRepository;

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
		System.out.println(authentication.getName());
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

	@Override
	public ResponseEntity<ApiResponse> createOrUpdateUserDetails(UserDetailRequest detailRequest) {
		try {
			User user = this.getLoggedInUser();
			Optional<UserSetting> setting = userSettingRepository.findByUserId(user.getId());
			Optional<UserAddress> address = userAddressRepository.findByUserId(user.getId());
			if (setting.isPresent()) {
				userSettingRepository.save(buildUserSetting(setting.get(), detailRequest));
			} else {
				userSettingRepository.save(buildUserSetting(detailRequest, user));
			}

			if (address.isPresent()) {
				userAddressRepository.save(buildUserAddress(address.get(), detailRequest));
			} else {
				userAddressRepository.save(buildUserAddress(detailRequest, user));
			}
			return ResponseMessage.ok(null);
		} catch (Exception ex) {
			return ResponseMessage.internalServerError(null);
		}

	}

	public UserSetting buildUserSetting(UserDetailRequest detailRequest, User user) {
		return UserSetting.builder()
				.contactNo(detailRequest.getContactNo())
				.dob(detailRequest.getDob())
				.gender(detailRequest.getGender())
				.alternativeContactNo(null)
				.status(true)
				.user(user)
				.build();
	}

	public UserAddress buildUserAddress(UserDetailRequest detailRequest, User user) {
		return UserAddress.builder()
				.addressLine1(detailRequest.getAddressLine1())
				.addressLine2(detailRequest.getAddressLine2() != null ? detailRequest.getAddressLine2() : null)
				.city(detailRequest.getCity())
				.state(detailRequest.getState())
				.postalCode(detailRequest.getPostalCode())
				.country(detailRequest.getCountry())
				.status(true)
				.user(user)
				.build();
	}

	public UserAddress buildUserAddress(UserAddress address, UserDetailRequest detailRequest) {
		address.setAddressLine1(detailRequest.getAddressLine1());
		address.setAddressLine2(detailRequest.getAddressLine2());
		address.setCity(detailRequest.getCity());
		address.setState(detailRequest.getState());
		address.setCountry(detailRequest.getCountry());
		address.setPostalCode(detailRequest.getPostalCode());
		return address;
	}

	public UserSetting buildUserSetting(UserSetting userSetting, UserDetailRequest detailRequest) {
		userSetting.setContactNo(detailRequest.getContactNo());
		userSetting.setDob(detailRequest.getDob());
		userSetting.setGender(detailRequest.getGender());
		return userSetting;
	}

}
