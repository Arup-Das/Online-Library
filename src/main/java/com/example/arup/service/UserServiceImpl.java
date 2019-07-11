package com.example.arup.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.arup.entity.Role;
import com.example.arup.entity.User;
import com.example.arup.model.UserModel;
import com.example.arup.repository.RoleRepository;
import com.example.arup.repository.UserRepository;
import com.example.arup.util.RoleName;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public UserModel findByUsername(String username)  throws UsernameNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		User userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		UserModel user = modelMapper.map(userEntity, UserModel.class);
		return user;
	}

	@Override
	public UserModel save(UserModel user) {
		Optional<User> storedUserEntity = userRepository.findByUsername(user.getUsername());
		if(storedUserEntity.isPresent()) {
			throw new RuntimeException("User with name "+user.getUsername()+" already exist.");
		}
		ModelMapper modelMapper = new ModelMapper();
		User userEntity = modelMapper.map(user, User.class);
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setActive(1);
		
		Set<Role> userRoles = new HashSet<>();
		Role role = roleRepository.findByRole(RoleName.USER_BASIC.name());
		userRoles.add(role);
		userEntity.setRoles(userRoles);
		User savedUserEntity = userRepository.save(userEntity);
		UserModel savedUser = modelMapper.map(savedUserEntity, UserModel.class);
		return savedUser;
	}
	
	

}
