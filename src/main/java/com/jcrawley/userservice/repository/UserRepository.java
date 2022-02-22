package com.jcrawley.userservice.repository;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer>{

	User findUserByEmail(String email);
	User findUserById(Integer id);
	void deleteUserById(Integer id);
	
}
