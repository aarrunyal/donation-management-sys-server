package com.donationmanagementsystem.controller.admin;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/user-setting")
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
//	
//	@PostMapping("/booked_seats")
//	public ResponseEntity<List<Integer>> getBookedSeat(@RequestBody HashMap<String, String> data){
//		List<Integer> seats = 	this.bookingService.getBookedSeat(data);
//		return new ResponseEntity<List<Integer>>(seats, HttpStatus.OK);
//	}
//	
//	@GetMapping("/inactive/{bookingId}")
////	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
//	public ResponseEntity<ApiResponse> cancelBooking(@PathVariable long bookingId){
//		boolean flag = this.bookingService.cancelBooking(bookingId);
//		return new ResponseEntity<ApiResponse>(new ApiResponse("Booking has been cancelled !!", true), HttpStatus.OK);
//	}
	
}
