package com.jcrawley.userservice.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.repository.User;
import com.jcrawley.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = User.builder()
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.tagline(userDto.getTagline())
				.dateCreated( Date.from(Instant.now()))
				.build();
		return getUserDto(userRepository.save(user));
	}
	

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteUserById(id);
	}
	

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		User user = userRepository.findUserById(id);
		user.setName(userDto.getName());
		user.setTagline(userDto.getTagline());		
		return getUserDto(user);
	}

	
	@Override
	public UserDto findUserById(Integer id) {
		User user = userRepository.findUserById(id);
		throw404IfNull(user);
		return getUserDto(user);
	}
	
	
	
	private void throw404IfNull(User user) {
		if(user == null) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);
		}
	}
	
	
	private UserDto getUserDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.name(user.getName())
				.tagline(user.getTagline())
				.password("").build();
	}

}
