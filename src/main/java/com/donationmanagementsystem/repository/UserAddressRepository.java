package com.donationmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}
