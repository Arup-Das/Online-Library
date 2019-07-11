package com.example.arup.service;

import java.util.Set;

import com.example.arup.model.UserInformationModel;
import com.example.arup.model.UserRegistrationModel;

public interface UserInformationService {
	public UserInformationModel findUserByEmail(String email);
	public UserInformationModel save(UserRegistrationModel userInfo);
	public Set<UserInformationModel> findUserByName(String name);
}
