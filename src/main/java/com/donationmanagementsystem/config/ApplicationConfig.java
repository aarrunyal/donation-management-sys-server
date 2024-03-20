package com.donationmanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.donationmanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	@Value("${image.upload.path}")
    private String uploadPath;

	private final UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService()    {
			return username -> userRepository.findByEmail(username)
					.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
    // public ClassLoaderTemplateResolver templateResolver() {
    //     ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    //     templateResolver.setPrefix("/templates/");
    //     templateResolver.setSuffix(".html");
    //     templateResolver.setTemplateMode(TemplateMode.HTML);
    //     templateResolver.setCacheable(false);
    //     templateResolver.setCharacterEncoding("UTF-8");
    //     return templateResolver;
    // }
	
	
}
