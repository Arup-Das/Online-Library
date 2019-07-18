package com.example.arup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.EmailVerificationToken;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
	List<EmailVerificationToken> findByUserUsername(String username);
    List<EmailVerificationToken> findByToken(String token);
}
