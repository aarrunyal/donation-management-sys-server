package com.jwtauthentication.JwtAuthentication.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jwtauthentication.JwtAuthentication.config.AppConstant;
import com.jwtauthentication.JwtAuthentication.exception.TokenMisMatchException;
import com.jwtauthentication.JwtAuthentication.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	@Autowired
	UserService userService;

	
	public String generateToken(String userName) { 
        Map<String, Object> claims = new HashMap<>(); 
        return createToken(claims, userName); 
    } 
	
	private String createToken(Map<String, Object> claims, String userName) { 
        return Jwts.builder() 
                .setClaims(claims) 
                .setSubject(userName) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + AppConstant.ACCESS_TOKEN_VALIDITY)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
    } 
	
	private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(AppConstant.SECRET_KEY); 
        return Keys.hmacShaKeyFor(keyBytes); 
    } 
	
	public String extractUsername(String token) { 
        return extractClaim(token, Claims::getSubject); 
    } 

	public Date extractExpiration(String token) { 
        return extractClaim(token, Claims::getExpiration); 
    } 
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws TokenMisMatchException{
        final Claims claims = extractAllClaims(token);        
        return claimsResolver.apply(claims); 
    }
	
	private  Claims extractAllClaims(String token)  { 
        Claims  claim =  Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
       return claim;
    } 
	
	private Boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date()); 
    } 
  
    public Boolean validateToken(String token, UserDetails userDetails) { 
        final String username = extractUsername(token); 
        User user = userService.getByEmail(username);
        //authenticates if the user email or user name matches with the extracted username
        return ((username.equals(user.getEmail())) || (username.equals(userDetails.getUsername())) && !isTokenExpired(token)); 
    } 
}
