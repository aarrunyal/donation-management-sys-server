package com.donationmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.donationmanagementsystem.utils.AppConstant;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebMvc
public class SecurityConfig {
	

	
	private final JwtAuthenticationFilter jwtAuthFilter;
	
	private final AuthenticationProvider authenticationProvider;
	
	private final LogoutHandler logoutHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
	        .cors().and().csrf()
	        .disable()
	        .authorizeHttpRequests(request ->
	        	request.requestMatchers(AppConstant.WHITE_LIST_URL)
	        	.permitAll()
	        	.requestMatchers("/api/v1/organiser/**").hasAnyRole(Role.ADMIN.name(), Role.ORGANISER.name())
	        	.requestMatchers(HttpMethod.GET, "/api/v1/organiser/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.ORGANISER_READ.name())
	        	.requestMatchers(HttpMethod.POST, "/api/v1/organiser/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.ORGANISER_CREATE.name())
	        	.requestMatchers(HttpMethod.PUT, "/api/v1/organiser/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.ORGANISER_UPDATE.name())
	        	.requestMatchers(HttpMethod.DELETE, "/api/v1/organiser/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.ORGANISER_DELETE.name())
//	        	// ad
//	        	.requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
//	        	.requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_READ.name())
//	        	.requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_CREATE.name())
//	        	.requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_UPDATE.name())
//	        	.requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_DELETE.name())
	        	
	        	.anyRequest()
	        	.authenticated()
	        )
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .logout()
	        .logoutUrl("/api/v1/auth/logout")
	        .addLogoutHandler(logoutHandler)
	        .logoutSuccessHandler(
	        	(request, response, authentication)->{
	        	SecurityContextHolder.clearContext();
	        });

	    return http.build();
	}
}
