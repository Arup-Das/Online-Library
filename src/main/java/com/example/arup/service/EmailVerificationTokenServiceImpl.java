package com.example.arup.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.arup.entity.EmailVerificationToken;
import com.example.arup.entity.User;
import com.example.arup.repository.EmailVerificationTokenRepository;
import com.example.arup.repository.UserRepository;
@Service
public class EmailVerificationTokenServiceImpl implements VerificationTokenService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailVerificationTokenRepository emailVerificationTokenRepository;
	@Autowired
	private MailService mailService;

	@Override
	public ResponseEntity<String> verifyUser(String token) {
		List<EmailVerificationToken> verificationTokens = emailVerificationTokenRepository.findByToken(token);
		if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }
		EmailVerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(EmailVerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActive(true);
        emailVerificationTokenRepository.save(verificationToken);

        return ResponseEntity.ok("You have successfully verified your email address.");
	}

	@Override
	public void sendVerificationToken(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" not found"));
		
		List<EmailVerificationToken> verificationTokens = emailVerificationTokenRepository.findByUserUsername(username);
		EmailVerificationToken verificationToken;
		if(verificationTokens.isEmpty()) {
			verificationToken = new EmailVerificationToken();
			verificationToken.setUser(user);
			emailVerificationTokenRepository.save(verificationToken);
		}else {
			verificationToken = verificationTokens.get(0);
		}
		mailService.sendVerificationMail(username, verificationToken.getToken());
	}

}
