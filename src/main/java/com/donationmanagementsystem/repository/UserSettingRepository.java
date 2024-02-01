package com.donationmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.UserSetting;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
	
	Optional<UserSetting> findByUserId(Long userId);
	
	Optional<UserSetting> findByUserIdAndUserSettingId(Long userId, Long userSettingId);

}
