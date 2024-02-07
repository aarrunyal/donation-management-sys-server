package com.donationmanagementsystem.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.donationmanagementsystem.config.JwtService;
import com.donationmanagementsystem.config.Role;
import com.donationmanagementsystem.entity.Token;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserVerification;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.repository.TokenRepository;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.repository.UserVerificationRepository;
import com.donationmanagementsystem.service.EmailService;
import com.donationmanagementsystem.utils.EmailDetails;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;

	private final PasswordEncoder encoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final TokenRepository tokenRepository;

	private final UserVerificationRepository userVerificationRepository;

	private final EmailService emailService;

	@Value("${app-url}")
	private String APP_URL;

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
		if (savedUserToken(savedUser, jwtToken)) {
			generateTokenForVerification(savedUser);
		}
		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		savedUserToken(user, jwtToken);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
		if (validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	private boolean savedUserToken(User user, String token) {
		revokeAllUserTokens(user);
		var newToken = Token
				.builder()
				.user(user)
				.token(token)
				.revoked(false)
				.expired(false)
				.build();
		tokenRepository.save(newToken);
		return true;
	}

	private void saveUserVerification(User user) {
		var userVerification = UserVerification
				.builder()
				.user(user)
				.token(Helper.getRandomToken(100))
				.expired(false).build();
		userVerificationRepository.save(userVerification);
	}

	private ResponseEntity<ApiResponse> generateTokenForVerification(User user) {
		Optional<UserVerification> userVerification = userVerificationRepository.findByUserIdAndExpired(user.getId());
		
		if(userVerification.isPresent()){
			var existingVerification= userVerification.get();
			existingVerification.setExpired(true);
			userVerificationRepository.save(existingVerification);
		}
		
		String randomText = Helper.getRandomToken(100);

		var verfication = UserVerification
				.builder()
				.user(user)
				.token(randomText)
				.expired(false).build();
		if (userVerificationRepository.save(verfication) != null) {
			var url = APP_URL + "/api/v1/auth/verify/" + randomText;
			var emailDetail = EmailDetails
					.builder()
					.msgBody("Please find below link to validate your profile \n\n" + url)
					.subject("Verify your account")
					.receipient(user.getEmail())
					.build();
			if (emailService.sendMail(emailDetail)) {
				return ResponseMessage.ok("Verificaiton email sent");
			}
		}
		return ResponseMessage.internalServerError(null);
	}

	public ResponseEntity<ApiResponse> verifyUser(String token) {
		UserVerification userVerification = userVerificationRepository.findByTokenAndExpired(token)
				.orElseThrow(() -> new ResourceNotFoundException("User verification", "token", null));
		User user = repository.findById(userVerification.getUser().getId())
				.orElseThrow(
						() -> new ResourceNotFoundException("User ", "user_id", userVerification.getUser().getId()));

		userVerification.setExpired(true);
		if (userVerificationRepository.save(userVerification) != null) {
			user.setVerified(true);
			repository.save(user);
			return ResponseMessage.ok("User verified successfully");
		}
		return ResponseMessage.ok("User cannot be verify at the moment. Please try again later");

	}

}
