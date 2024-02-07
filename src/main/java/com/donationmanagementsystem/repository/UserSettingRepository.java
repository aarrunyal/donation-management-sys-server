package com.donationmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.entity.UserSetting;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
	
	Optional<UserSetting> findByUserId(Long userId);
	
	
	Optional<UserSetting> findByUserIdAndId(@Param("userId") Long userId,@Param("userSettingId") Long userSettingId);

}
