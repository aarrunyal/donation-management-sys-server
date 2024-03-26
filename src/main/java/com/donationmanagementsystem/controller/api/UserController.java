package com.donationmanagementsystem.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.donationmanagementsystem.payload.request.UserRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminUserController")
@RequestMapping("/api/v1/admin/user")
@CacheConfig(cacheNames = "users")
// @PreAuthorize("hasRole('ADMIN')")
public class UserController {

    // 
    // @Caching(
    // evict = {@CacheEvict(value = "productList", allEntries = true)},
    // put = {@CachePut(value = "product", key = "#id")}
    // )

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> get() {
        return userService.all();
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> post(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", beforeInvocation = true)
    public ResponseEntity<ApiResponse> post(@PathVariable("id") Long userId) {
        return userService.delete(userId);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody UserRequest userRequest,
            @PathVariable Long id) {
        return userService.update(userRequest, id);
    }
}
