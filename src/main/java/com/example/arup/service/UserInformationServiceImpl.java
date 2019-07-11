package com.example.arup.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.arup.entity.UserInfo;
import com.example.arup.model.UserInformationModel;
import com.example.arup.model.UserModel;
import com.example.arup.model.UserRegistrationModel;
import com.example.arup.repository.UserInfoRepository;

@Service
@Transactional
public class UserInformationServiceImpl implements UserInformationService {
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public UserInformationModel findUserByEmail(String email) {
		UserInfo userInfoEntity = userInfoRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User with email "+email+" not found."));
		ModelMapper modelMapper = new ModelMapper();
		UserInformationModel userInformation = modelMapper.map(userInfoEntity, UserInformationModel.class);
		return userInformation;
	}

	@Override
	public UserInformationModel save(UserRegistrationModel registerUserInfo) {
		UserModel user = new UserModel();
		user.setUsername(registerUserInfo.getEmail());
		user.setPassword(registerUserInfo.getPassword());
		userService.save(user);
		ModelMapper modelMapper = new ModelMapper();
		UserInfo userInfoEntity = modelMapper.map(registerUserInfo, UserInfo.class);
		UserInfo storedUserInfoEntity = userInfoRepository.save(userInfoEntity);
		UserInformationModel storedUserInfo = modelMapper.map(storedUserInfoEntity, UserInformationModel.class);
		return storedUserInfo;
	}

	@Override
	public Set<UserInformationModel> findUserByName(String name) {
		Set<UserInfo> userInfoEntitySet = userInfoRepository.findByFirstName(name);
		ModelMapper modelMapper = new ModelMapper();
		return userInfoEntitySet.stream().map(userInfoEntity -> modelMapper.map(userInfoEntity, UserInformationModel.class)).collect(Collectors.toSet());
	}

}
