package com.example.arup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
