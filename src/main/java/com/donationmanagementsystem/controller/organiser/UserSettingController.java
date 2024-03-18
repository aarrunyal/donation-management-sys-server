package com.donationmanagementsystem.controller.organiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserSettingRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserSettingResponse;
import com.donationmanagementsystem.service.UserService;
import com.donationmanagementsystem.service.UserSettingService;

import jakarta.validation.Valid;

@RestController("UserSettingController")
@RequestMapping("/api/v1/user-setting")
public class UserSettingController {


	@Autowired
	private UserSettingService userSettingService;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse> post(@Valid @RequestBody UserSettingRequest userSettingRequest){
		User user = userService.getLoggedInUser();
		return userSettingService.create(userSettingRequest, user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@Valid @RequestBody UserSettingRequest userSettingRequest, @PathVariable Long id){
		User user = userService.getLoggedInUser();
		return userSettingService.update(userSettingRequest, id, user);
	}


	@GetMapping("")
	public ResponseEntity<UserSettingResponse> get(){
		User user = userService.getLoggedInUser();
		return userSettingService.show(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
		User user = userService.getLoggedInUser();
		return this.userSettingService.delete(id, user);
	}
}
