package com.jcrawley.userservice.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String email;
	
	@Column
    @NotBlank(message = "Name is mandatory")
	private String name;

	@Column
	private String password;
	
	@CreationTimestamp
	@Column(updatable=false)
	private Date dateCreated;
	
	@Column
    @NotBlank(message = "Tagline is mandatory")
	private String tagline;
	
}
