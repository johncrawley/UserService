package com.jcrawley.userservice.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired	
	private UserService userService;
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer id){
		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);		
	}
	
	
	@PostMapping()
	@PreAuthorize("isAnonymous()")
	public ResponseEntity<UserDto> saveNewUser(@Valid @RequestBody UserDto userDto){
		UserDto savedDto = userService.saveUser(userDto);
		return new ResponseEntity<>(savedDto, getLocationHeaderFor(userDto), HttpStatus.CREATED);	
	}
	
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
		UserDto updatedUserDto = userService.updateUser(id,userDto);
		return new ResponseEntity<>(updatedUserDto, HttpStatus.NO_CONTENT);
	}
	

	private HttpHeaders getLocationHeaderFor(UserDto userDto) {
		HttpHeaders headers = new HttpHeaders();
		if(userDto != null) {
			headers.add("Location", "api/v1/user/" + userDto.getId().toString());
		}
		return headers;
	}
	

}
