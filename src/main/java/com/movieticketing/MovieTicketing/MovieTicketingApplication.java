package com.movieticketing.MovieTicketing;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.movieticketing.MovieTicketing.model.User;
import com.movieticketing.MovieTicketing.repository.UserRepository;
//https://www.geeksforgeeks.org/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/
@SpringBootApplication
public class MovieTicketingApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MovieTicketingApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
