package com.donationmanagementsystem;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.donationmanagementsystem.model.User;
import com.donationmanagementsystem.repository.UserRepository;
//https://www.geeksforgeeks.org/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/
//https://medium.com/javarevisited/creating-a-springboot-crud-api-using-the-jackson-objectmapper-a3457ec158ef
@SpringBootApplication
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DonationManagementSystem {

	
	public static void main(String[] args) {
		SpringApplication.run(DonationManagementSystem.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	



}
