package com.example.authservice.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.authservice.entity.UserCredential;
import com.example.authservice.repository.UserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredential> credential = userCredentialRepository.findByName(username);
		return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential!!"));
	}

}
