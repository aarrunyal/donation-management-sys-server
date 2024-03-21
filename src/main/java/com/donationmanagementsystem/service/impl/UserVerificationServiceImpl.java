package com.donationmanagementsystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserVerification;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.repository.UserVerificationRepository;
import com.donationmanagementsystem.service.UserVerificationService;

import jakarta.annotation.Resource;

@Service
public class UserVerificationServiceImpl implements UserVerificationService {

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Autowired
    private UserRepository userRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("User verfication", "user id", user.getId()));
        oldUserVerification.setExpired(userVerification.isExpired());
        return userVerificationRepository.save(oldUserVerification);
    }

    @Override
    public boolean verifyUser(String code) {
        try {
            UserVerification userVerification = userVerificationRepository.findByToken(code);
            if (userVerification != null && userVerification.expired == false) {
                User user = userRepository.findById(userVerification.getUser().getId()).orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", userVerification.getUser().getId()));

                userVerification.setExpired(true);
                userVerificationRepository.save(userVerification);
                user.setVerified(true);
                userRepository.save(user);
                return true;
            }
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

}
