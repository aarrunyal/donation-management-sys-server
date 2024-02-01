package com.donationmanagementsystem.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.utils.AppConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {



	public <T> T extraClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String extractUserName(String token) {
		return extraClaim(token, Claims::getSubject);
	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(AppConstant.SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public  boolean isTokenValid(String token, UserDetails userDetails) 
	{
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extraClaim(token, Claims::getExpiration);
	}
	
	

}
