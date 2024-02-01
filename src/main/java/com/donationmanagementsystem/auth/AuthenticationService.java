package com.donationmanagementsystem.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.config.JwtService;
import com.donationmanagementsystem.config.Role;
import com.donationmanagementsystem.entity.Token;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.repository.TokenRepository;
import com.donationmanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	private final TokenRepository tokenRepository;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(encoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();
		var savedUser = repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		savedUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder().token(jwtToken).build();
		
	}

	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
				);
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		savedUserToken(user, jwtToken);
		return AuthenticationResponse.builder().token(jwtToken).build();
		
	}
	
	
	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
		if(validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(token-> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
	
	private void savedUserToken(User user, String token) {
		revokeAllUserTokens(user);
		var newToken = Token
				.builder()
				.user(user)
				.token(token)
				.revoked(false)
				.expired(false)
				.build();
		tokenRepository.save(newToken);
	}

	

}
