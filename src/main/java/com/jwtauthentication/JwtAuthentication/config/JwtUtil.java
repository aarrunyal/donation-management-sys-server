package com.jwtauthentication.JwtAuthentication.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.jwtauthentication.JwtAuthentication.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

//@Component
public class JwtUtil {

	private final String SECRET_KEY= "mysecret_key";
	private long ACCESS_TOKEN_VALIDITY = 60 *60 *100;
	
	private JwtParser jwtParser;
	
	private final String TOKEN_HEADER = "Authorization";
	private final String TOKEN_PREFIX = "Bearer ";
	
	public String createToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("first_name", user.getFirstName());
		claims.put("last_name", user.getLastName());
		Date tokenCreateTime = new Date();
		Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(ACCESS_TOKEN_VALIDITY));
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(tokenValidity)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public Claims resolveClaims(HttpServletRequest request) {
		try {
			String token = resolveToken(request);
			if(token != null) {
				return parseJwtClaims(token);
			}
			return null;
		}catch (ExpiredJwtException e) {
			request.setAttribute("Expired", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			request.setAttribute("Invalid", e.getMessage());
			throw e;
		}
	}
	
	public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
	
	private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
