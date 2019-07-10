package com.example.arup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
