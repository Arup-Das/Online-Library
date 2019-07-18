package com.example.arup.service;

import org.springframework.http.ResponseEntity;

public interface VerificationTokenService {
	public void sendVerificationToken(String username);
	public ResponseEntity<String> verifyUser(String token);
}
