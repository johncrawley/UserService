package com.jcrawley.userservice.service;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.repository.User;

public interface UserService {

	User saveUser(User user);
	void deleteUser(UserDto user);
	void updateUser(UserDto user, Integer id);
	User findUserByEmail(String email);
	
}
