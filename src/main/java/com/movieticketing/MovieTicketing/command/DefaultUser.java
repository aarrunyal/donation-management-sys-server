package com.movieticketing.MovieTicketing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.movieticketing.MovieTicketing.model.User;
import com.movieticketing.MovieTicketing.repository.UserRepository;

@Component
public class DefaultUser implements CommandLineRunner {

	@Value("${default-user.email}")
	private String email;

	@Value("${default-user.password}")
	private String password;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		int count = userRepository.countByEmail(this.email);
		if (count <= 0) {
			User user = new User();
			user.setEmail(this.email);
			user.setFirstName("Super");
			user.setLastName("Admin");
			user.setRoles("ROLE_ADMIN");
			user.setPassword(new BCryptPasswordEncoder().encode(this.password));
			userRepository.save(user);
		}

	}

}
