package com.jcrawley.userservice.service;



import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jcrawley.userservice.repository.User;
import com.jcrawley.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	
public class UserServiceNoDtoImpl implements UserServiceNoDto {

	private final UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		User user2 = User.builder()
				.name(user.getName())
				.email(user.getEmail())
				.password(user.getPassword())
				.tagline(user.getTagline())
				.dateCreated( Date.from(Instant.now()))
				.build();
		return userRepository.save(user2);
	}
	

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteUserById(id);
	}
	

	@Override
	public User updateUser(Integer id, User amendedUser) {
		User user = userRepository.findUserById(id);
		user.setName(amendedUser.getName());
		user.setTagline(amendedUser.getTagline());		
		return user;
	}

	
	@Override
	public User findUserById(Integer id) {
		User user = userRepository.findUserById(id);
		throw404IfNull(user);
		return user;
	}
	
	
	
	private void throw404IfNull(User user) {
		if(user == null) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);
		}
	}
	


}