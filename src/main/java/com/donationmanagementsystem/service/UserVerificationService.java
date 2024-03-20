package com.donationmanagementsystem.service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserVerification;


public interface UserVerificationService {
	
	UserVerification create(UserVerification userVerification, User user);

	UserVerification update(UserVerification userVerification, User user);

    boolean verifyUser(String code);
}
