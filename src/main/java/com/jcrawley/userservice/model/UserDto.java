package com.jcrawley.userservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserDto {

	@Id
	private Integer id;
	@Email
	//@JsonProperty("email")
	private String email;
	@NotNull
	@Size(min=3, max=24)
	private String name;
	 
	@NotBlank(message = "Tagline is mandatory")
	private String tagline;
	@NotNull
	@JsonProperty("password")
	@Size(min=7, max=24)
	private String password;
}
