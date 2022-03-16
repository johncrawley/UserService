package com.jcrawley.userservice.service;


import com.jcrawley.userservice.repository.User;

public interface UserServiceNoDto {
	User saveUser(User user);
	void deleteUser(Integer id);
	User updateUser(Integer id, User user);
	User findUserById(Integer id);
}
