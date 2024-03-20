package com.donationmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.entity.UserVerification;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long>  {

    Optional<UserVerification> findByUserId(Long id);

    @Query("SELECT v FROM UserVerification v where v.user.id=:id and expired=false")
    Optional<UserVerification> findByUserIdAndExpired(@Param("id") Long id);


    @Query("SELECT v FROM UserVerification v where v.token=:token and expired=false")
    Optional<UserVerification> findByTokenAndExpired(@Param("token") String token);

    UserVerification findByToken(String code);
    
}
