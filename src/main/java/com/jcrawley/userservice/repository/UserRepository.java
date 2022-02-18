package com.jcrawley.userservice.repository;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer>{

	User findUserByEmail(String email);
	void deleteUserByEmail(String email);
	
}
