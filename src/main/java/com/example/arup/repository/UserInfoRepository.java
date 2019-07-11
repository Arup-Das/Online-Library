package com.example.arup.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	public Optional<UserInfo> findByEmail(String email);
	public Set<UserInfo> findByFirstName(String firstName);
}
