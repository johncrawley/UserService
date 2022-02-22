package com.jcrawley.userservice.service;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.repository.User;

public interface UserService {

	UserDto saveUser(UserDto user);
	void deleteUser(Integer id);
	UserDto updateUser(Integer id, UserDto user);
	UserDto findUserById(Integer id);
	
}
