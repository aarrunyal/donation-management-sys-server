package com.donationmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.model.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long>{

	@Query("SELECT t FROM Theater t where status=:status ORDER BY id DESC")
	List<Theater> getByStatus(@Param("status") boolean status);
}
