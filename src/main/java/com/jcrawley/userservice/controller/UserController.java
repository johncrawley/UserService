package com.jcrawley.userservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jcrawley.userservice.model.UserDto;
import com.jcrawley.userservice.repository.Temp;
import com.jcrawley.userservice.repository.User;
import com.jcrawley.userservice.service.UserService;
import com.jcrawley.userservice.service.UserServiceNoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired	
	private UserService userService;
	
	@Autowired
	private UserServiceNoDto userServiceNoDto;
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer id){
		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);		
	}
	
	
	@PostMapping()
	@Validated
	public ResponseEntity<UserDto> saveNewUser(@Valid @RequestBody UserDto userDto){
		UserDto savedDto = userService.saveUser(userDto);
		return new ResponseEntity<>(savedDto, getLocationHeaderFor(userDto), HttpStatus.CREATED);	
	}
	

	@PostMapping("/temp")
	public ResponseEntity<String> save(@Valid @RequestBody Temp temp){
		System.out.println("Temp x: "+ temp.getX());
		return ResponseEntity.ok("It was ok!");
	}
	

	@GetMapping("/temp/{tempStr}/{tempInt}")
	public ResponseEntity<String> getTemp(
			@PathVariable @Size(min = 4, max = 6) String tempStr,
			@PathVariable @Max(4) Integer tempInt){
		return ResponseEntity.ok("It was ok get: " + tempStr);
	}
	
	
	@PostMapping("/nodto")
	public ResponseEntity<User> save(@Valid @RequestBody User user){
		return ResponseEntity.ok(userServiceNoDto.saveUser(user));
	}
	

	private HttpHeaders getLocationHeaderFor(UserDto userDto) {
		HttpHeaders headers = new HttpHeaders();
		if(userDto != null) {
			headers.add("Location", "api/v1/user/" + userDto.getId().toString());
		}
		return headers;
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
	

}
