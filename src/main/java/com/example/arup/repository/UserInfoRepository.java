package com.example.arup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arup.entity.UserInfo;
import com.example.arup.model.UserRegistrationModel;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	public Optional<UserInfo> findByEmail(String email);
	public UserInfo save(UserRegistrationModel userInfo);
}
