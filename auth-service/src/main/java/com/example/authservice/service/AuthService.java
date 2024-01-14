package com.example.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authservice.entity.UserCredential;
import com.example.authservice.repository.UserCredentialRepository;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	public String saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		userCredentialRepository.save(credential);
		return "User added to the system!";
	}
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
}
