package com.example.arup.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.arup.model.UserModel;

public interface UserService extends UserDetailsService{
	public UserModel findByUsername(String username) throws UsernameNotFoundException ;
	public UserModel save(UserModel user);
}
