package com.donationmanagementsystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserVerification;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.repository.UserVerificationRepository;
import com.donationmanagementsystem.service.UserVerificationService;

public class UserVerificationServiceImpl implements UserVerificationService {

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Override
    public UserVerification create(UserVerification userVerification, User user) {
        Optional<UserVerification> oldUserVerification = userVerificationRepository.findByUserId(user.getId());

        if (oldUserVerification.isEmpty()) {
            return null;
        }
        return userVerificationRepository.save(userVerification);
    }

    @Override
    public UserVerification update(UserVerification userVerification, User user) {
        UserVerification oldUserVerification = userVerificationRepository.findByUserId(user.getId())
        .orElseThrow(()->new ResourceNotFoundException("User verfication", "user id", user.getId()));
            oldUserVerification.setExpired(userVerification.isExpired());
        return userVerificationRepository.save(oldUserVerification);
    }

}
