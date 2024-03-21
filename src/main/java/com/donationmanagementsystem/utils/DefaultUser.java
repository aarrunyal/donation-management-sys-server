package com.donationmanagementsystem.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.donationmanagementsystem.config.Role;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultUser implements CommandLineRunner {

	@Value("${default-admin-user.email}")
	private String adminEmail;

	@Value("${default-admin-user.password}")
	private String adminPassword;
	
	@Value("${default-admin-user.name}")
	private String adminName;
	
	@Value("${default-organiser-user.email}")
	private String organiserEmail;

	@Value("${default-organiser-user.password}")
	private String organiserPassword;
	
	@Value("${default-organiser-user.name}")
	private String organiserName;

	private final UserRepository userRepository;
	
	private final PasswordEncoder encoder;
	

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.findByEmail(this.adminEmail).orElse(null);
		if(user == null) {
			var newUser = User
			.builder()
			.firstName(adminName)
			.lastName(adminName)
			.email(adminEmail)
			.role(Role.ADMIN)
			.password(encoder.encode(adminPassword))
			.verified(true)
			.build();
			System.out.println("Admin === "+ newUser.toString());
			userRepository.save(newUser);
		}
		
		user = userRepository.findByEmail(this.organiserEmail).orElse(null);
		if(user == null) {
			var newUser = User
			.builder()
			.firstName(organiserName)
			.lastName(organiserName)
			.email(organiserEmail)
			.role(Role.ORGANISER)
			.password(encoder.encode(organiserPassword))
			.verified(true)
			.build();
			System.out.println("Organiser === "+ newUser.toString());
			userRepository.save(newUser);
		}

	}

}
