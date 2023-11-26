package com.movieticketing.MovieTicketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketing.MovieTicketing.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
