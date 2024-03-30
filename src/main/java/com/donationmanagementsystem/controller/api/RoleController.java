package com.donationmanagementsystem.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.service.UserService;
import com.donationmanagementsystem.utils.Helper;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/admin/role")
@CacheConfig(cacheNames = "permission")
@Slf4j
public class RoleController {

    @Autowired
    UserService userService;

    @GetMapping("/permissions")

    public List<String> getPermissionByRole() {
        User user = userService.getLoggedInUser();
        log.info("____________________________________");
        log.info("Getting Permissions from database ");
        log.info("____________________________________");
        return user.getAuthorities()
                .stream()
                .map(authority -> Helper.replaceCharFromString(authority.toString(), ":", "_"))
                .collect(Collectors.toList());
    }

}
