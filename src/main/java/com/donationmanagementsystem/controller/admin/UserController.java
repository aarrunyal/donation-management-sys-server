package com.donationmanagementsystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.UserRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminUserController")
@RequestMapping("/api/v1/admin/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> get() {
        return userService.all();
    }

  
    @PostMapping("")
    public ResponseEntity<ApiResponse> post(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.getLoggedInUser();
        return userService.create(userRequest, user);
    }
}
