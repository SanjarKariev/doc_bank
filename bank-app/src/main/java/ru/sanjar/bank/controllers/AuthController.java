package ru.sanjar.bank.controllers;

import javax.validation.Valid;

import ru.sanjar.bank.payload.response.ServerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sanjar.bank.models.User;
import ru.sanjar.bank.payload.request.LoginRequest;
import ru.sanjar.bank.payload.request.SignupRequest;
import ru.sanjar.bank.payload.response.JwtResponse;
import ru.sanjar.bank.repository.UserRepository;
import ru.sanjar.bank.security.jwt.JwtUtils;
import ru.sanjar.bank.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws JsonProcessingException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ServerResponse.response(true, new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				userDetails.getFirstName(),
				userDetails.getSecondName(),
				userDetails.getMiddleName()), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws JsonProcessingException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ServerResponse.response(false, "username_already_used", HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ServerResponse.response(false, "email_already_used", HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getFirstName(),
				signUpRequest.getSecondName(),
				signUpRequest.getMiddleName(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		userRepository.save(user);

		return ServerResponse.response(true, "registered_successful", HttpStatus.OK);
	}
}
