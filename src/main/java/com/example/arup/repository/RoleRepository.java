package com.example.arup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
