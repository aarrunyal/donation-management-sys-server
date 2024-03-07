package com.donationmanagementsystem.config;


import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.donationmanagementsystem.entity.User;

public class UsernameAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
 
        return Optional.ofNullable(((User) authentication.getPrincipal()).getId());
    }

    
}
