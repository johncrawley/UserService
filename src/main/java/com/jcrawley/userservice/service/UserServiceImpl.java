package com.jcrawley.userservice.service;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.repository.User;
import com.jcrawley.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public User saveUser(User userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(UserDto userDto) {
		
		userRepository.deleteUserByEmail(userDto.getEmail());
		
	}

	@Override
	public void updateUser(UserDto userDto, Integer id) {
		User user1 = User.builder()
				.name(userDto.getName())
				.email(userDto.getEmail())
				.tagline(userDto.getTagline()).build();
		User user = userRepository.findUserByEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setTagline(userDto.getTagline());				
	}

	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

}
