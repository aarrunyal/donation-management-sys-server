package com.jwtauthentication.JwtAuthentication.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwtauthentication.JwtAuthentication.exception.TokenMisMatchException;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.service.JwtService;
import com.jwtauthentication.JwtAuthentication.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	 @Autowired
	    private JwtService jwtService; 
	  
	    @Autowired
	    private UserService userDetailsService; 
	  
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
	        String authHeader = request.getHeader(AppConstant.TOKEN_HEADER); 
	        String token = null; 
	        String username = null; 
	        if (authHeader != null && authHeader.startsWith(AppConstant.TOKEN_PREFIX)) { 
	            token = authHeader.substring(7);
	            username = jwtService.extractUsername(token); 
	        }
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
	            if (jwtService.validateToken(token, userDetails)) { 
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
	                SecurityContextHolder.getContext().setAuthentication(authToken); 
	            } else
	            	throw new TokenMisMatchException();
	        } 
	        filterChain.doFilter(request, response); 
	    } 

}