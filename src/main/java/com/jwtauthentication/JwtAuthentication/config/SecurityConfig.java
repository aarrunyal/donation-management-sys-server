package com.jwtauthentication.JwtAuthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.jwtauthentication.JwtAuthentication.service.CustomUserDetailsService;


//@Configuration 
//@EnableWebSecurity
////Above annotation configure customized configuration
//public class SecurityConfig{
//	
//	private CustomUserDetailsService userDetailsService;
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests().
//		requestMatchers("/rest/auth/").permitAll()
//		.anyRequest().authenticated()
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		return http.build();
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Bean
//	public NoOpPasswordEncoder passwordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder) throws Exception{
//		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
//        return authenticationManagerBuilder.build();
//	}
//}
