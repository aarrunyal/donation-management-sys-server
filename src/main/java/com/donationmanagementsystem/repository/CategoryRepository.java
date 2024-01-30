package com.donationmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
