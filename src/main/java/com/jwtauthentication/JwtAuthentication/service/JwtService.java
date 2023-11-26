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
	
	public static final String  SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	private final String TOKEN_HEADER = "Authorization";
	private final String TOKEN_PREFIX = "Bearer ";
	private long ACCESS_TOKEN_VALIDITY = 60 *60 *100;
	
	public String generateToken(String userName) { 
        Map<String, Object> claims = new HashMap<>(); 
        return createToken(claims, userName); 
    } 
	
	private String createToken(Map<String, Object> claims, String userName) { 
        return Jwts.builder() 
                .setClaims(claims) 
                .setSubject(userName) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
    } 
	
	private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY); 
        return Keys.hmacShaKeyFor(keyBytes); 
    } 
	
	public String extractUsername(String token) { 
        return extractClaim(token, Claims::getSubject); 
    } 

	public Date extractExpiration(String token) { 
        return extractClaim(token, Claims::getExpiration); 
    } 
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    }
	
	private Claims extractAllClaims(String token) { 
        return Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
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
