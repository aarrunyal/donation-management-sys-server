package com.donationmanagementsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.donationmanagementsystem.auth.AuthenticationService;
import com.donationmanagementsystem.auth.RegisterRequest;
import com.donationmanagementsystem.config.Role;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
		return args ->{
			var admin = RegisterRequest
					.builder()
					.firstName("admin")
					.lastName("admin")
					.email("admin@dms.com")
					.password("admin@123")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token : "+ authenticationService.register(admin).getToken());
			
			var organiser = RegisterRequest
					.builder()
					.firstName("organiser")
					.lastName("organiser")
					.email("organiser@dms.com")
					.password("organiser@123")
					.role(Role.ORGANISER)
					.build();
			System.out.println("Organiser token : "+ authenticationService.register(organiser).getToken());	
			
		};
		
	}

}
