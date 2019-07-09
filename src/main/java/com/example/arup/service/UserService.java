package com.example.arup.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.arup.entity.User;

public interface UserService extends UserDetailsService{
	public User findByEmail(String email);
}
