package com.donationmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    Optional<UserAddress> findByUserId(Long id);

    Optional<UserAddress> findByUserIdAndId(Long id, Long userAddressId);

    List<UserAddress> findAllByUserId(Long id);

}
