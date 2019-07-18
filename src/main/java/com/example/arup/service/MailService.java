package com.example.arup.service;

public interface MailService {
	public boolean sendMail(String toEmail, String subject, String body);
	public boolean sendVerificationMail(String toEmail, String verificationCode);
}
