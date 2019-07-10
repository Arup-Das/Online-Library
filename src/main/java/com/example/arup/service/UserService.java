package com.example.arup.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.arup.entity.User;

public interface UserService extends UserDetailsService{
	public User findByUsername(String username) throws UsernameNotFoundException ;
}
