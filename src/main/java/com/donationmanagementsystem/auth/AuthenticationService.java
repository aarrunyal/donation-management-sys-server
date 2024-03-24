package com.donationmanagementsystem.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

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

	public ResponseEntity<ApiResponse> register(RegisterRequest request) {
		List<User> existingUser = repository.findAllByEmail(request.getEmail());
		if (existingUser.size() > 0) {
			return ResponseMessage.notAcceptable("User already exist");
		}
		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(encoder.encode(request.getPassword()))
				.role(request.getRole() != null ? request.getRole() : Role.USER)
				.build();
		var savedUser = repository.save(user);
		if (savedUser.getId() != null) {
			generateTokenForVerification(savedUser);
			return ResponseMessage.ok("User created successfully");
		}
		return ResponseMessage.internalServerError(null);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		System.out.println("Here");
		var user = repository.findByEmailAndVerified(request.getEmail(), true)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email", request.getEmail()));
		System.out.println("Exception");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), request.getPassword()));
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

	private ResponseEntity<ApiResponse> generateTokenForVerification(User user) {
		Optional<UserVerification> userVerification = userVerificationRepository.findByUserIdAndExpired(user.getId());

		if (userVerification.isPresent()) {
			var existingVerification = userVerification.get();
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
			var url = APP_URL + "/auth/verify/" + randomText;

			Context context = new Context();
			context.setVariable("name", user.getFirstName());
			context.setVariable("link", url);

			var emailDetail = EmailDetails
					.builder()
					.msgBody("Please find below link to validate your profile \n\n" + url)
					.subject("Verify your account")
					.receipient(user.getEmail())
					.templateName("email/new-account.html")
					.build();
			if (emailService.sendMailWithHtmlTemplate(emailDetail, context)) {
				// if (emailService.sendMail(emailDetail)) {
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
