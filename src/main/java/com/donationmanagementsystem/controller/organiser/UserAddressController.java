package com.donationmanagementsystem.controller.organiser;

import java.util.List;

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
import com.donationmanagementsystem.payload.request.UserAddressRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserAddressResponse;
import com.donationmanagementsystem.service.UserAddressService;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/organiser/user-address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<ApiResponse> post(@Valid @RequestBody UserAddressRequest userAddressRequest) {
        User user = userService.getLoggedInUser();
        return userAddressService.create(userAddressRequest, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody UserAddressRequest userAddressRequest,
            @PathVariable Long id) {
        User user = userService.getLoggedInUser();
        return userAddressService.update(userAddressRequest, id, user);
    }

    @GetMapping("")
    public ResponseEntity<List<UserAddressResponse>> get() {
        User user = userService.getLoggedInUser();
        return userAddressService.all(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        User user = userService.getLoggedInUser();
        return this.userAddressService.delete(id, user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressResponse> show(@PathVariable Long id) {
        User user = userService.getLoggedInUser();
        return this.userAddressService.show(id, user);
    }
    
}
